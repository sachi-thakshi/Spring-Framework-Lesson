package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.A;
import lk.ijse.gdse.bean.B;
import lk.ijse.gdse.bean.SpringBeanOne;
import lk.ijse.gdse.bean.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({AppConfig2.class})
public class AppConfig1 {
//    @Bean
//    public SpringBeanOne springBeanOne() {
//        return new SpringBeanOne();
//    }
//
//    @Bean
//    public AppConfig2 appConfig2() {
//        return new AppConfig2();
//    }

    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b(){
        return new B();
    }
}
