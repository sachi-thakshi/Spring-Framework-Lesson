package lk.ijse.gdse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Boy implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

  @Autowired
  @Qualifier("girl1")
  Agreement girl;

  public Boy(){
      System.out.println("Boy constructor");
  }
  public void chatWithGirl() {
//      Girl girl = new Girl();
      girl.chat();
  }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory Boy");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName Boy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Boy destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Boy afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Boy applicationContext");
    }
}
