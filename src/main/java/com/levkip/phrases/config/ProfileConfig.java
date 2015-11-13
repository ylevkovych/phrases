package com.levkip.phrases.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.IOException;

/**
 * Created by levy on 11.10.15.
 */
public class ProfileConfig implements ApplicationContextInitializer<ConfigurableWebApplicationContext>
{

    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        try {
            ResourcePropertySource ps = new ResourcePropertySource("classpath:properties/application-profile.properties");
            applicationContext.getEnvironment().getPropertySources().addFirst(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
