package mybatis.mapper;

import mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    //多条件查询（if标签）
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    //多条件查询（where标签）
    List<Car> selectByMultiConditionWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    //多条件查询（trim标签）
    List<Car> selectByMultiConditionTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    //更新语句（set标签）
    int updateByIdSet(Car car);

    //判断查询（choose where otherwise）
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    //批量处理（for each）
    int deleteByIds(@Param("ids")Long[] ids);
    int insertBatch(@Param("cars") List<Car> cars);
}
