package mybatis.mapper;

import mybatis.pojo.Clazz;
import mybatis.pojo.Student;

public interface StudentMapper {

    //一条SQL语句，根据id获取学生信息以及关联的班级信息
    Student selectById(Integer id);

    //一条SQL语句，Association查询
    Student selectByIdAssociation(Integer id);

    //两条SQL语句，分步查询,第一步
    Student selectByIdStepFirst(Integer id);

}
