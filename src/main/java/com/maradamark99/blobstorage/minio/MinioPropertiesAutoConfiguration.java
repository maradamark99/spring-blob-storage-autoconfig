package com.maradamark99.blobstorage.minio;

import com.maradamark99.blobstorage.Constants;
import io.minio.MinioClient;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@ConditionalOnMissingBean(MinioProperties.class)
@AutoConfiguration
@ConfigurationProperties(prefix = Constants.ENV_ROOT_PREFIX)
@ConfigurationPropertiesScan
public class MinioPropertiesAutoConfiguration {

    private String accessKey;

    private String secretKey;

    private String endpoint;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Bean
    @ConditionalOnClass(MinioClient.class)
    @ConditionalOnMissingBean(MinioProperties.class)
    MinioProperties minioProperties() {
        return new MinioProperties(endpoint, accessKey, secretKey);
    }

}
