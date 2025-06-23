package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.SpringBean;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("lk.ijse.gdse.bean")
@Import({AppConfig1.class, AppConfig2.class})

//if config file in the root folder
//@ImportResource("classpath:hibernate.cfg.xml")
// if not the root
@ImportResource("file:absolute-path-of-hibernate.cfg.xml")
public class AppConfig {
//    @Bean
//    public SpringBean springBean() {
//        return new SpringBean();
//    }
}
