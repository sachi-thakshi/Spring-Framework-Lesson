package lk.ijse.gdse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {

    @Value("sachini")
    private String name;

    public SpringBean() {
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
    }


//    @Autowired(required = false)
//    public SpringBean(@Value("car") String value) {
//        System.out.println(value);
//    }
//
//    @Autowired(required = false)@Value("sachini") String name, @Value("6") int number, @Value("true") boolean b
//    public SpringBean() {
//        System.out.println("SpringBean Constructor " + name);
//        System.out.println("SpringBean Constructor " + number);
//        System.out.println("SpringBean Constructor " + b);
//    }
}
