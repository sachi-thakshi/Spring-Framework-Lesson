package lk.ijse.gdse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    // 01. Instantiation --> the state of object creation
    public MyConnection() {
        System.out.println("MyConnection Constructor");
    }

    // 02. Population of Properties --> there is no method to find state of properties

    // 03. BeanNameAware --> BeanNameAware (BeanID)
    @Override
    public void setBeanName(String name) {
        System.out.println("MyConnection setBeanName Called");
    }

    // 04. BeanFactoryAware --> BeanFactoryAware (DI)
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyConnection setBeanFactory Called");
    }

    // 05.Application Context Aware --> ApplicationContextAware (AOP & DP)
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyConnection setApplicationContext Called");
    }

    // 06. Bean Initialization --> InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyConnection afterPropertiesSet Called");
    }

    //  07. Disposable Bean --> DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("MyConnection is destroyed");
    }



}
