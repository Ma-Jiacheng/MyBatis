import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ConfigurationTest {
    @Test
    public void testEnvironment() throws IOException {
        //获取SqlSessionFactory对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //不指定环境id，默认使用default配置的环境
        //SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        //可以增加一个参数，指定使用哪个环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "mybatisDB");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Object> cars = sqlSession.selectList("selectAll");
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //SqlSessionFactory一个数据库创建一个，不要频繁创建该对象
        //通过SqlSessionFactory对象，可以开启多个对话
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        //一号会话，com.mysql.cj.jdbc.ConnectionImpl@413f69cc
/*        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        sqlSession1.insert("insertCar");
        sqlSession1.commit();
        sqlSession1.close();
        //二号会话，com.mysql.cj.jdbc.ConnectionImpl@65a15628
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        sqlSession2.insert("insertCar");
        sqlSession2.commit();
        sqlSession2.close();*/
        //不使用连接池，没创建一个会话，都会新建一个连接对象
        //使用连接池，会使用同一个连接对象com.mysql.cj.jdbc.ConnectionImpl@6d167f58

        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertCar");
            //测试连接池数量上限，不进行关闭
        }
    }
}
