package mapper;

import pojo.Car;

import java.util.List;

public interface CarMapper {

    int insert(Car car);

    int deleteById(int id);

    int update(Car car);

    Car selectById(Long id);

    List<Car> selectAll();
}
