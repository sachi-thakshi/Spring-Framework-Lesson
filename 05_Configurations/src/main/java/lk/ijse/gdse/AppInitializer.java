package lk.ijse.gdse;

import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.bean.SpringBeanOne;
import lk.ijse.gdse.bean.SpringBeanTwo;
import lk.ijse.gdse.config.AppConfig;
import lk.ijse.gdse.config.AppConfig1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBean bean = context.getBean(SpringBean.class);
        System.out.println(bean);

//        // Configuration Class 1
//        SpringBeanOne bean1 = context.getBean(SpringBeanOne.class);
//        System.out.println(bean1);
//
//        // Configuration Class 2
//        SpringBeanTwo bean2 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean2);
//
//        SpringBeanTwo bean3 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean3);

        context.registerShutdownHook();
    }
}