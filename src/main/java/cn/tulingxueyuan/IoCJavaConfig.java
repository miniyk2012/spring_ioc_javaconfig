package cn.tulingxueyuan;

import cn.tulingxueyuan.beans.BeanA;
import cn.tulingxueyuan.beans.BeanB;
import cn.tulingxueyuan.beans.InterfaceA;
import cn.tulingxueyuan.beans.MyImportBeanDefinitionRegistrar;
import cn.tulingxueyuan.beans.MyImportSelector;
import cn.tulingxueyuan.beans.Role;
import cn.tulingxueyuan.beans.User;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
// 用户标记一个spring配置类,之前是根据xml启动spring上下文
// 相当于 xml文件  <beans></beans>
@Configuration
@ComponentScan(basePackages = "cn.tulingxueyuan")
//== <context:component-scan base-package="cn.tulingxueyuan" >
// 使用@PropertySource 引入外部属性资源文件
@PropertySource("classpath:db.properties")
/* @Import
  1 导入其他的配置类@Import(SecondJavaConfig.class)
  2.导入类注册为Bean @Import(Role.class)
  3.导入ImportSelector实现类，可以注册多个bean@Import(MyImportSelector.class)
  4.导入ImportBeanDefinitionRegistrar实现类，可以注册多个BeanDefinition
*/
@Import({MyImportBeanDefinitionRegistrar.class, SecondJavaConfig.class, MyImportSelector.class})
public class IoCJavaConfig {

  /*
  <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
          <property name="username" value="${mysql.username}"></property>
          <property name="password" value="${mysql.password}"></property>
          <property name="url"  value="${mysql.url}"></property>
          <property name="driverClassName" value="${mysql.driverClassName}"></property>
      </bean>
  */
  @Value("${mysql.username}")
  private String username;
  @Value("${mysql.password}")
  private String password;
  @Value("${mysql.url}")
  private String url;
  @Value("${mysql.driverClassName}")
  private String driverClassName;


  /**
   * 可以将一个类的实例(可以干预Bean实例化过程),注册为一个Bean 会自动将返回值作为Bean的类型    将方法名作为bean的名字
   *
   * @Bean(name = "dd") 设置bean的名字及别名（替换）
   * <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
   * @Bean(initMethod = "",destroyMethod = "") = <bean class="xx" id="xx" init-method="initByConfig"
   * destroy-method="destroyByConfig"></bean>
   * <p>
   * 怎么去自动依赖外部Bean:直接在方法里面写上需要依赖的参数即可,不需要写@Autowired 怎么去自动依赖内部Bean：直接调用方法即可
   */
  @Bean(name = {"dataSource", "dd"})
  //@Scope("prototype")  //作用域
  public DruidDataSource dataSource(Role role,
      @Value("${mysql.driverClassName}") String driverClassName,
      @Value("${mysql.maxActive}") Integer maxActive) {
    System.out.printf("dataSource.role=%s\n", role);
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setUrl(url);
    dataSource.setDriverClassName(driverClassName);
    dataSource.setMaxActive(maxActive);
    System.out.printf("user3=%s\n", user3());
    return dataSource;
  }


  @Bean
  public User user3() {
    return new User();
  }

  @Bean
  @Primary
  public InterfaceA beanA() {
    return new BeanA();
  }

  @Bean
  public InterfaceA beanB() {
    return new BeanB();
  }
}
