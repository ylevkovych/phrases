package com.levkip.phrases.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by levy on 11.10.15.
 */
@Configuration
public class ConfigProperties {

    @Bean
    static public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[]
        {
                new ClassPathResource("properties/db.properties"),
                //new ClassPathResource("properties/application-common.properties"),
                //new ClassPathResource("properties/CmsWebServiceApiConstants.properties"),
                //new ClassPathResource("properties/MiscConstants.properties"),
        };
        pspc.setLocations(resourceLocations);

        pspc.setIgnoreUnresolvablePlaceholders(true);

        return pspc;
    }

}
