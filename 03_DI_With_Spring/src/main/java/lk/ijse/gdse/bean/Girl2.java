package lk.ijse.gdse.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // check life cycle
public class Girl2 implements Agreement, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

    public Girl2() {
        System.out.println("Girl 2 Constructor");
    }


    @Override
    public void chat() {
        System.out.println("Girl 2 chatting ....");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Girl 2 BeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Girl 2 BeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Girl 2 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Girl 2 after properties set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Girl 2 application context");
    }
}

