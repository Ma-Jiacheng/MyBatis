package mybatis.mapper;

import mybatis.pojo.Car;

import java.util.List;
import java.util.Map;

public interface CarMapper {
    //根据id查询一条
    Car selectById(Long id);
    //查询所有结果
    List<Car> selectAll();
    //根据某个字段进行模糊查询
    List<Car> selectByBrandLike(String brand);
    //使用Map集合接收查询对象
    Map<String, Object> selectByIdRtnMap(Long id);
    //查询结果映射——Map
    List<Car> selectAllByResultMap();
    //返回总记录条数
    Long selectCount();
}
