package lk.ijse.gdse.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 implements DIInterface{

    // --------------->>>> Property Injection
//    @Autowired
//    DI test1 ;
//
//    public Test2() {
//        System.out.println("Test2 constructor called");
//    }
//
//    public void calledHelloMethod() {
//        test1.sayHello();
//    }

    // --------------->>>> Constructor through Injection
//    DI test1;
//
//    @Autowired // optional
//    public  Test2(DI t1) {
//        this.test1 = t1;
//    }
//    public void calledHelloMethod(){
//        test1.sayHello();
//    }

    // --------------->>>> Setter Method through Injection
//    DI test1;
//
//    @Autowired
//    public void setTest1(DI test1) {
//        this.test1 = test1;
//        System.out.println("Setter Method called");
//    }
//
//    public void calledHelloMethod() {
//        test1.sayHello();
//    }

    // --------------->>>> Interface through Injection
    DI test1;

    @Override
    @Autowired
    public void inject(DI test1) {
        this.test1 = test1;
    }

    public void calledHelloMethod(){
        test1.sayHello();
    }
}
