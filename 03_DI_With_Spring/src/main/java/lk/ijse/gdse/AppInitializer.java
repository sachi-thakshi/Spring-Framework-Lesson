package lk.ijse.gdse;

import lk.ijse.gdse.bean.Boy;
import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.config.AppConfig;
import lk.ijse.gdse.di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Test2 bean = context.getBean(Test2.class);
        bean.calledHelloMethod();

        context.registerShutdownHook();
    }
}