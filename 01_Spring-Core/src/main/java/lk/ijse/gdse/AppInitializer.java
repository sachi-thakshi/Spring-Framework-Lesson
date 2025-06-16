package lk.ijse.gdse;

import lk.ijse.gdse.bean.MyConnection;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class); // put to the annotation
        context.refresh();

//        SpringBean bean1 = context.getBean(SpringBean.class); // print all object s.out
//        bean1.testBean(); // get instance
//        System.out.println(bean1);

//        ------------------ To check that object creation ------------------
//        SpringBean bean2 = context.getBean(SpringBean.class);
//        bean2.testBean();
//        System.out.println(bean2);

//        TestBean1 testBean1 = context.getBean(TestBean1.class);
//        System.out.println(testBean1);
//
//        // ------ Request from BeanID (Class Name (first letter -> simple letter))
//        TestBean1 byBeanID = (TestBean1) context.getBean("testBean1");
//        System.out.println(byBeanID);
//
//        TestBean2 testBean2 = context.getBean("sachini",TestBean2.class);
//        System.out.println(testBean2);
//
//        TestBean3 testBean3 = context.getBean(TestBean3.class);
//        System.out.println(testBean3);

//        Runtime.getRuntime().addShutdownHook(new Thread(()-> {  // -> 01
//            System.out.println("JVM is about to be shut down");
//            context.close();
//        }));

        MyConnection myConnection = (MyConnection) context.getBean("sachi");
        System.out.println(myConnection);

        context.registerShutdownHook(); // -> 02
//        context.close();


    }
}