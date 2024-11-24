package cn.tulingxueyuan.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class User {

    /*
    使用 @Value设置依赖注入的属性
    1.除了可以写硬编码值
    2.还可以写${}  、#{}
    * */
    //@Value("#{role.name}")
    @Value("杨恺")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer age;

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

/*public User() {
        System.out.println("User已加载");
    }

    private void initByConfig() {
        System.out.println("User初始化");
    }

    private void destroyByConfig() {
        System.out.println("User销毁");
    }*/
}
