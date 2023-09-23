package javassist;

import bank.dao.AccountDao;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavassistTest {
    @Test
    public void testGenerateFirstClass() throws Exception {
        //获取类池，这个类池用来生成Class
        ClassPool pool = ClassPool.getDefault();
        //指定类名
        CtClass ctClass = pool.makeClass("bank.dao.AccountImpl");
        //制造方法
        String methodCode = "public void insert(){System.out.println(10086);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //将方法添加至类中
        ctClass.addMethod(ctMethod);
        //在内存中生成Class
        ctClass.toClass();
        //类加载，返回AccountDaoImpl类的字节码
        Class<?> aClass = Class.forName("bank.dao.AccountImpl");
        //创建对象
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        Object object = declaredConstructor.newInstance();
        //获取方法
        Method insertMethod = aClass.getDeclaredMethod("insert");
        //调用方法
        insertMethod.invoke(object);
    }

    @Test
    public void testGenerateImpl() throws Exception {
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("bank.dao.AccountDaoImpl");
        //制造接口
        CtClass ctInterface = pool.makeInterface("bank.dao.AccountDao");
        //添加接口至类中
        ctClass.addInterface(ctInterface);
        //实现接口中的方法
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"hello,delete!\");}", ctClass);
        ctClass.addMethod(ctMethod);
        //在内存中生成类
        Class<?> aClass = ctClass.toClass();
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        //获取对象
        Object object = declaredConstructor.newInstance();
        //获取方法
        Method deleteMethod = aClass.getDeclaredMethod("delete");
        //调用方法
        deleteMethod.invoke(object);
    }

    @Test
    public void testGenerateAccountDaoImpl() throws Exception {
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("bank.dao.AccountDaoImpl");
        //制造接口
        CtClass ctInterface = pool.makeInterface("bank.dao.AccountDao");
        //实现接口
        ctClass.addInterface(ctInterface);
        //实现接口中所有方法
        // 1. 获取接口中所有的方法
        Method[] methods = AccountDao.class.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            try {
                //拼接方法体
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");   //修饰符列表
                methodCode.append(method.getReturnType().getName());    //返回值类型
                methodCode.append(" ");
                methodCode.append(method.getName());    //追加方法名
                methodCode.append("(");
                //拼接参数 String actno, Double balance
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg" + i);
                    if (i != parameterTypes.length - 1){
                        methodCode.append(",");
                    }
                }
                methodCode.append("){System.out.println(1124);");
                //添加return语句
                String returnTypeSimpleName = method.getReturnType().getSimpleName();
                if ("void".equals(returnTypeSimpleName)) {

                } else if ("int".equals(returnTypeSimpleName)){
                    methodCode.append("return 1;");
                } else if ("String".equals(returnTypeSimpleName)){
                    methodCode.append("return \"hello\";");
                }
                methodCode.append("}");
                //System.out.println(methodCode);

                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //在内存中生成class
        Class<?> aClass = ctClass.toClass();
        //创建对象
        AccountDao accountDao = (AccountDao) aClass.newInstance();
        //调用对象
        accountDao.insert();
        accountDao.delete("A");
        accountDao.update("B", 5.31);
        accountDao.select("C");
    }
}
