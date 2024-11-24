package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.IoCJavaConfig;
import cn.tulingxueyuan.beans.Person;
import cn.tulingxueyuan.beans.Role;
import cn.tulingxueyuan.beans.User;
import cn.tulingxueyuan.beans.Wife;
import cn.tulingxueyuan.controller.UserController;
import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class JavaConfigTest {
    AnnotationConfigApplicationContext ioc;

    @Before
    public void before(){
        ioc=new AnnotationConfigApplicationContext(IoCJavaConfig.class);
    }

    @Test
    public  void test01(){
        UserController bean = ioc.getBean(UserController.class);
        bean.getUser();

    }


    @Test
    public  void test02() throws SQLException {
        DruidDataSource dataSource = ioc.getBean(DruidDataSource.class,"dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(dataSource);
    }

    @Test
    public  void test03(){
        User bean = (User) ioc.getBean("user2");
        System.out.printf("user2=%s\n",bean);

    }

    @Test
    public  void test04(){
        Role bean =   ioc.getBean(Role.class);
        System.out.println(bean);

    }


    /**
     * @Import(MyImportSelector.class)
     */
    @Test
    public  void test05(){
        Person bean = ioc.getBean(Person.class);
        System.out.println(bean.getName());

        Wife wife = ioc.getBean(Wife.class);
        System.out.println(wife);
    }

    /**
     * @Import(MyImportBeanDefinitionRegistrar.class)
     */
    @Test
    public  void test06(){
        Person bean = (Person) ioc.getBean("personx");
        System.out.println(bean.getName());
    }
}
