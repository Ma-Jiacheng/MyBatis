package mybatis.mapper;

import mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    //查询所有汽车信息
    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    //根据汽车类型获取汽车信息
    List<Car> selectByCarType(String carType);

    //批量删除汽车信息
    int deleteBatch(String ids);

    //根据汽车品牌模糊查询
    List<Car> selectByBrandLike(String brand);
}
