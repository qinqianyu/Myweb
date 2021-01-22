package com.jxk.myWeb;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.io.File;

/**
 * @author jxk
 */
@SpringBootApplication
public class MyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.config.location", matchIfMissing = false)
    public PropertiesConfiguration propertiesConfiguration(
            @Value("${spring.config.location}") String path) throws Exception {
        System.out.println(path);
        String filePath = new File(path.substring("file:".length())).getCanonicalPath();
        PropertiesConfiguration configuration = new PropertiesConfiguration(new File(filePath));
        configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
        return configuration;
    }
}
