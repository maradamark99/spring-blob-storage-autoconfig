package com.maradamark99.blobstorage.minio;

import com.maradamark99.blobstorage.Constants;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = Constants.ROOT_PREFIX)
public class MinioPropertiesAutoConfiguration {

    String accessKey;

    String secretKey;

    String endpoint;

    @Bean
    @ConditionalOnClass(MinioClient.class)
    @ConditionalOnMissingBean(MinioProperties.class)
    MinioProperties minioProperties() {
        return new MinioProperties(endpoint, accessKey, secretKey);
    }

}
