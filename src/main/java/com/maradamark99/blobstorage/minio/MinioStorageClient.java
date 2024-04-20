package com.maradamark99.blobstorage.minio;

import com.maradamark99.blobstorage.StorageClient;
import com.maradamark99.blobstorage.exception.FileUploadException;
import io.minio.*;

import java.io.IOException;
import java.io.InputStream;

public class MinioStorageClient implements StorageClient {
    
    private final MinioClient minioClient;

    public MinioStorageClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void putObject(String bucket, String object, InputStream file) throws FileUploadException, IOException {
        var uploadArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .stream(file, -1, 1024 * 1024 * 5)
                .build();
        try {
            minioClient.putObject(uploadArgs);
        } catch (Exception e) {
            throw new FileUploadException();
        }
    }

    @Override
    public byte[] getObject(String bucket, String object) {
        var getObjectArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .build();
        return readBytesFromStream(getObjectArgs);
    }

    @Override
    public byte[] getObject(String bucket, String object, long offset, long length) {
        var getObjectArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .offset(offset)
                .length(length)
                .build();
        return readBytesFromStream(getObjectArgs);
    }

    @Override
    public void makeBucket(String bucket) {
        try {
            var makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(bucket)
                    .build();
            minioClient.makeBucket(makeBucketArgs);
        }
        catch (Exception e) {

        }
    }

    @Override
    public boolean bucketExists(String bucket) {
        var bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(bucket)
                .build();
        try {
            return minioClient.bucketExists(bucketExistsArgs);
        } catch (Exception e) {
            return false;
        }
    }

    private byte[] readBytesFromStream(GetObjectArgs getObjectArgs) {
        try (InputStream stream = minioClient.getObject(getObjectArgs)) {
            return stream.readAllBytes();
        }
        catch (Exception e) {
            return null;
        }
    }
}
