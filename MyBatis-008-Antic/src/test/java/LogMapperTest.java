import mybatis.mapper.LogMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import mybatis.pojo.Log;
import mybatis.util.SqlSessionUtil;

import java.util.List;

public class LogMapperTest {
    @Test
    public void testSelectAllByTable(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper mapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = mapper.selectAllByTable("20230918");
        logs.forEach(log -> System.out.println(log));
        sqlSession.close();
    }
}
