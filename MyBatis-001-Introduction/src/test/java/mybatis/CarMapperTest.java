package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import util.SqlSessionUtil;

public class CarMapperTest {
    @Test
    public void testInsertCar(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config.xml"));
            //开启会话（底层会开启事务）
            sqlSession = sqlSessionFactory.openSession();
            //执行SQL语句，处理相关业务
            sqlSession.insert("insertCar");
            //如果没有发生异常，提交事务
            sqlSession.commit();
        } catch (Exception e) {
            //发生异常，建议回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            //关闭会话（释放资源）
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsertCarByUtil(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行SQL语句，处理相关业务
        int count = sqlSession.insert("insertCar");
        System.out.println("更新" + count + "条记录");
        //如果没有发生异常，提交事务
        sqlSession.commit();
        //关闭事务
        sqlSession.close();
    }

}
