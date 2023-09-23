import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

public class CarMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //第一次执行编译查询SQL语句 select * from t_car where id = ?
        Car car1 = mapper.selectById(6L);
        System.out.println(car1);
        //第二次查询不在执行SQL语句
        Car car2 = mapper.selectById(6L);
        System.out.println(car2);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdCache() throws IOException {
        //创建一个sqlSessionFactory对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        /*不使用二级缓存*/
        //该行代码执行结束后，car1的数据存放到一级缓存
        //Car car1 = mapper1.selectByIdCache(5L);
        //System.out.println(car1);

        //不关闭sqlSession1对象，二级缓存中不会有数据

        //该行代码执行结束后，car2的数据存放到另一个一级缓存
        //Car car2 = mapper2.selectByIdCache(5L);
        //System.out.println(car2);

        //程序执行到这里，会将sqlSession1一级缓存中的数据，写入二级缓存
        //sqlSession1.close();
        //程序执行到这里，会将sqlSession2一级缓存中的数据，写入二级缓存
        //sqlSession2.close();


        /*使用二级缓存*/
        //该行代码执行结束后，car1的数据存放到一级缓存
        Car car1 = mapper1.selectByIdCache(5L);
        System.out.println(car1);
        //将sqlSession1一级缓存中的数据，写入二级缓存
        sqlSession1.close();

        //此时执行sqlSession2会使用二级缓存
        Car car2 = mapper2.selectByIdCache(5L);
        System.out.println(car2);
        sqlSession2.close();
    }
}
