import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {
    @Test
    public void testParseMyBatisConfigXML() throws Exception {
        //创建SAXReader对象
        SAXReader saxReader = new SAXReader();
        //获取输入流
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        //读取XML文件
        Document document = saxReader.read(inputStream);

        //获取文档中的根标签
        //Element rootElement = document.getRootElement();
        //String rootElementName = rootElement.getName();
        //System.out.println("根节点名字：" + rootElementName);

        //获取default默认环境id
        String xpath = "/configuration/environments";      //xpath是做标签路径匹配的，能让我们快速定位XML文件中的元素
        Element environments = (Element) document.selectSingleNode(xpath);  //Element是Node的子类，方法更多，使用更便捷
        String defaultEnvironmentID = environments.attributeValue("default");
        System.out.println("默认环境ID：" + defaultEnvironmentID);

        //获取具体的环境
        xpath = "/configuration/environments/environment[@id = '" + defaultEnvironmentID + "']";
        //System.out.println(xpath);
        Element environment = (Element) document.selectSingleNode(xpath);
        //System.out.println(environment);

        //获取environment节点下transactionManager节点
        Element transactionManager = environment.element("transactionManager");
        String transactionManagerType = transactionManager.attributeValue("type");
        System.out.println("事务管理器类型：" + transactionManagerType);

        //获取transactionManager节点下的dataSource节点
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源类型：" + dataSourceType);

        //获取dataSource节点下的所有子节点
        List<Element> propertyElements = dataSource.elements();
        propertyElements.forEach(propertyEle -> {
            String name = propertyEle.attributeValue("name");
            String value = propertyEle.attributeValue("value");
            System.out.println(name + " = " + value);
        });

        //获取所有Mapper标签
        xpath = "//mapper";     //不从根路径开始，从任意位置开始获取所有的某个标签，xpath应该这样写
        List<Node> mappers = document.selectNodes(xpath);
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String resource = mapperElt.attributeValue("resource");
            System.out.println("Mapper配置文件：" + resource);
        });
    }

    @Test
    public void testParseSQLMapperXML() throws Exception {
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = saxReader.read(inputStream);

        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println("命名空间：" + namespace);

        List<Element> elements = mapper.elements();

        elements.forEach(element -> {
            String id = element.attributeValue("id");
            System.out.println("SQL语句ID：" + id);
            //获取标签中的SQL语句，并且去除前后空白
            String textTrim = element.getTextTrim();
            System.out.println("标签的语句内容：" + textTrim);
            //MyBatis封装的JDBC，必须执行使用？作为占位符的SQL语句
            String sql = textTrim.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println("可执行的SQL语句：" + sql);
        });
    }
}
