package mybatis.mapper;
import mybatis.pojo.Car;

public interface CarMapper {

    Car selectById(Long id);

    Car selectByIdCache(Long id);
}
