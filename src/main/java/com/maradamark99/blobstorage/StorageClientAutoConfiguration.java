package com.maradamark99.blobstorage;

import com.maradamark99.blobstorage.minio.MinioStorageClient;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class StorageClientAutoConfiguration {

    @Bean
    @ConditionalOnClass(MinioClient.class)
    @ConditionalOnMissingBean(StorageClient.class)
    StorageClient storageClient(MinioClient minioClient) {
        return new MinioStorageClient(minioClient);
    }

}
