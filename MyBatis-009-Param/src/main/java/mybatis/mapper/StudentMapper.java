package mybatis.mapper;

import mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    //接口方法参数只有一个且为简单数据类型
    List<Student> selectById(Long id);
    //接口方法参数只有一个，非简单数据类型
    int insertStudentByMap(Map<String, Object> map);

    //多参数情况
    //List<Student> selectByNameAndSex(String name, Character sex);
    List<Student> selectByNameAndSex(@Param("name") String name, @Param("sex") Character sex);   //使用@Param注解
}
