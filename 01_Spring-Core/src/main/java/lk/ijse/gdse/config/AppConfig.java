package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.MyConnection;
import lk.ijse.gdse.test.TestBean1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "lk.ijse.gdse.bean") // @ComponentScan -> scan components in bean package define that object
@ComponentScan(basePackageClasses = TestBean1.class)
public class AppConfig {
    @Bean("sachi")
    MyConnection getConnection() {
        return new MyConnection();
    }
}
