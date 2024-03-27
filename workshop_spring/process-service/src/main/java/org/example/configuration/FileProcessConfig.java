package org.example.configuration;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Configuration
public class FileProcessConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}

// Port configuration using WebServerFactoryCustomizer implements
/*public class FileProcessConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8080);
    }
}*/
