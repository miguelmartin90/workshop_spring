package org.example.dto;

import org.example.model.FileMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileMetadataBean {

    @Bean
    public FileMetadata fileMetadata(){
     return new FileMetadata();
    }
}
