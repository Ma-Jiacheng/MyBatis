package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlSessionUtil {
    //为了防止工具类new对象，工具类的构造方法通常是私有化的
    //工具类中的所有方法都是静态的，直接使用类名即可调用，不需要new对象
    private SqlSessionUtil(){}

    //SqlSessionFactory对象，一个对象对应一个environment
    //类加载时执行，SqlSessionUtil工具类在进行第一次加载的时候，解析xml文件，创建SqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
