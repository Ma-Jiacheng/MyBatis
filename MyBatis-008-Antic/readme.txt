MyBatis小技巧
1. #{} 与 ${} 的区别 ?
    #{} ：底层使用PreparedStatement，特点是先进行SQL语句的编译，然后给SQL语句的 ？ 传值
    ${} ：底层使用Statement，先进行SQL语句的拼接，然后再对SQL语句进行编译，存在SQL注入的风险

    优先使用#{}，这是原则，避免SQL注入风险。
    如果需要使用SQL语句的关键字放到SQL语句中，只能使用${}，因为#{}是以值的形式放至SQL语句中的

2. 向SQL语句中拼接表名，就需要使用${}
    现实业务中，可能会存在分表存储数据的情况，因为一张表存储数据量太大，查询效率低。可以将这些数据有规律的分表存储，这样查询效率比较高。
    例如以日期为表名的日志表：select * from log_${datetime}，需要使用${}拼接表名

3. 批量删除，一次删除多条记录
    批量删除的SQL语句有两种写法：
        第一种（or）：delete from t_car where id = 1 or id = 2 or id = 3;
        第二种（in）：delete from t_car where id in(1, 2, 3);
    应该采用${}：delete from t_car where id in(${ids})

4. 模糊查询：like
    需求：根据部分名称进行查询
    语句（以品牌查询）：select * from t_car where brand like '%奔驰%'
        第一种写法：select * from t_car where brand like '%${brand}%'
        第二种写法：select * from t_car where brand like concat('%', #{brand}, '%')
        第三种写法：select * from t_car where brand like concat('%', '${brand}', '%')
        第四种写法：select * from t_car where brand like "%"#{brand}"%"

5. 关于MyBatis中的别名机制
    <typeAliases>
        <!--自己指定别名-->
        <typeAlias type="pojo.Car" alias="CarClass"/>
        <typeAlias type="pojo.Log" alias="LogClass"/>

        <!--采用默认别名-->
        <typeAlias type="pojo.Car"/>
        <typeAlias type="pojo.Log"/>

        <!--包下所有的类自动起默认别名-->
        <package name="pojo"/>
    </typeAliases>

    所有别名不区分大小写，namespace不能使用别名机制，默认别名为类的简名（mybatis.pojo.Car --> Car）

6. config文件中的mappers标签
    mapper标签的属性可以有三个：
        resource：从类的根路径下开始查找资源，配置文件需要放在类路径当中
        url：使用绝对路径，不要求配置文件必须放在类路径当中，只需要提供绝对路径
        class：提供mapper接口的全限定接口名，MyBatis会自动在同级目录中寻找xxxMapper.xml文件（xxxMapper.xml和xxxMapper接口必须在同一包下）