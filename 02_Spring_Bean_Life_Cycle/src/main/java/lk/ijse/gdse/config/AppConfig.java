package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.MyConnection;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.bean")
public class AppConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // --> didn't print Bean Life Cycle
//    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // --> printed Bean Life Cycle - ByDeafult Singleton
//    @Scope("prototype")
    MyConnection getMyConnection() {
        return new MyConnection();
    }

}
