package lk.ijse.gdse;

import lk.ijse.gdse.bean.MyConnection;
import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        SpringBean springBean1 = context.getBean(SpringBean.class);
//        System.out.println(springBean1);
//
//        SpringBean springBean2 = context.getBean(SpringBean.class);
//        System.out.println(springBean2);

        MyConnection myConnection1 = context.getBean(MyConnection.class);
//        System.out.println("MyConnection 1 : " + myConnection1);

        MyConnection myConnection2 = context.getBean(MyConnection.class);
//        System.out.println("MyConnection 2 : " +myConnection2);

        context.registerShutdownHook();
//        context.close();
    }
}