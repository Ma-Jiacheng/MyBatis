package mybatis.mapper;

import java.util.List;
import mybatis.pojo.Car;

public interface CarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Car row);

    Car selectByPrimaryKey(Long id);

    List<Car> selectAll();

    int updateByPrimaryKey(Car row);
}