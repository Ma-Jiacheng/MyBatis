package mybatis.mapper;
import mybatis.pojo.Car;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface CarMapper {

    @Insert("insert into t_car values(null, #{carNum}, #{brand}, #{guidePrice}, #{productTime}, #{carType})")
    int insert(Car car);

    @Delete("delete from t_car where id = #{id}")
    int delete(Long id);
}
