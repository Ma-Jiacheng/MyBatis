package mybatis.mapper;

import mybatis.pojo.Log;

import java.util.List;

public interface LogMapper {

    //根据日期查询不同的表，获取表中日志
    List<Log> selectAllByTable(String date);
}
