package lk.ijse.gdse;

import lk.ijse.gdse.bean.Boy;
import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Boy boy = context.getBean(Boy.class);
        System.out.println(boy);
        boy.chatWithGirl();

        context.registerShutdownHook();
    }
}