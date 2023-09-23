package mybatis.mapper;

import mybatis.pojo.Clazz;

public interface ClazzMapper {

    //两条SQL语句，分步查询,第二步
    Clazz selectByIdStepSecond(Integer id);

    //一对多，根据班级信息查询班级成员
    Clazz selectByCollection(Integer id);
}
