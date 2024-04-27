package com.maradamark99.blobstorage.minio;

import io.minio.MinioClient;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@AutoConfigureAfter(PropertyPlaceholderAutoConfiguration.class)
public class MinioClientAutoConfiguration {

    @Bean
    @ConditionalOnClass(MinioClient.class)
    @ConditionalOnMissingBean(MinioClient.class)
    MinioClient minioClient(MinioProperties properties) {
        return MinioClient.builder()
                .endpoint(properties.endpoint())
                .credentials(properties.accessKey(), properties.secretKey())
                .build();
    }

}
