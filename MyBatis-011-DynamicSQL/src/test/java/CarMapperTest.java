import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByMultiCondition(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition("奔驰", 40.00, "燃油车");
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWhere(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWhere(null, null, "燃油车");
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionTrim(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionTrim(null, null, "燃油车");
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(6L, null, "AMG-One", 160.00, null, "燃油车");
        int count = mapper.updateByIdSet(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectByChoose(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose(null, 26.00, "燃油车");
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void deleteByIds(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids = {4L, 5L, 6L};
        int count = mapper.deleteByIds(ids);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertBatch(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car01 = new Car(null, "1104", "帕萨特", 17.00, "2020-11-02", "燃油车");
        Car car02 = new Car(null, "1105", "秦-Plus", 13.00, "2020-09-21", "新能源");
        Car car03 = new Car(null, "1106", "蒙迪欧", 18.00, "2020-8-07", "燃油车");
        List<Car> cars = new ArrayList<>();
        cars.add(car01);
        cars.add(car02);
        cars.add(car03);
        int count = mapper.insertBatch(cars);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
