package cn.tulingxueyuan.service;


import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public interface BaseService<T>{

    T getBean();

    //公共的增删查改
}
