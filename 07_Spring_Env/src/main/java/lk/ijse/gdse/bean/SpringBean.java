package lk.ijse.gdse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {
    @Value("${os.name}")
    private String osName;

    @Value("${user.name}")
    private String logName;

    @Value("${db.name}")
    private String dbName;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.url}")
    private String dbUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("O/S Name: " + osName);
        System.out.println("Name: " + logName);
        System.out.println("DB Name: " + dbName);
        System.out.println("DB Password: " + dbPassword);
        System.out.println("DB User: " + dbUser);
        System.out.println("DB URL: " + dbUrl);
    }
}
