package lk.ijse.gdse;

import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBean bean = context.getBean(SpringBean.class);
        System.out.println("BeanID : " + bean);

        context.registerShutdownHook();
    }
}