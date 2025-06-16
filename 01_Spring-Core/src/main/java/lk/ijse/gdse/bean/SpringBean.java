package lk.ijse.gdse.bean;

import org.springframework.stereotype.Component;

@Component // Object create
public class SpringBean {
    public SpringBean() { // constructor
        System.out.println("SpringBean Object Created");
    }

    public void testBean(){
        System.out.println("TestBean Method Called");
    }
}
