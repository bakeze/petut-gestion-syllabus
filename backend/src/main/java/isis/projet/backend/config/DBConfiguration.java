package isis.projet.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties
@SuppressWarnings("unused")
public class DBConfiguration {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("Data base Connection for Dev");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to Dev - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        System.out.println("Data base Connection for TEST");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to Dev - postgres";
    }


    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        System.out.println("Data base Connection for production");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to Prod - Azure";
    }



}