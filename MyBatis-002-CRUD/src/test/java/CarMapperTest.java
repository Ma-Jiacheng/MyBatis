import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import util.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {
    @Test
    public void testInsertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //将Map集合中的数据进行封装
        Map<String, Object> map = new HashMap<>();
        map.put("car_num", "1008");
        map.put("brand", "问界M5");
        map.put("guide_price", 25.00);
        map.put("product_time", "2023-08-01");
        map.put("cat_type", "新能源车");

        //insert方法参数insert(sqlID, 对象)
        int count = sqlSession.insert("insertCar", map);
        System.out.println("更新了" + count + "条记录");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null, "1010", "唐-DMi", 17.00, "2021-10-20", "新能源车");
        sqlSession.insert("insertCarByPojo", car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByID(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        sqlSession.delete("deleteByID", 12);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateByID(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        sqlSession.update("updateByID", 10);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByID(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Object car = sqlSession.selectOne("selectByID", 7);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Object> allCar = sqlSession.selectList("selectAll");

        allCar.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testNameSpace(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Object> allCar = sqlSession.selectList("UserMapper.selectAll");

        allCar.forEach(car -> System.out.println(car));
        sqlSession.close();
    }
}
