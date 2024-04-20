package com.maradamark99.blobstorage;

import com.maradamark99.blobstorage.exception.FileUploadException;

import java.io.IOException;
import java.io.InputStream;

public interface StorageClient {

    byte[] getObject(String bucket, String object);

    byte[] getObject(String bucket, String object, long offset, long length);

    void makeBucket(String bucket);

    boolean bucketExists(String bucket);

    void putObject(String bucket, String object, InputStream file) throws FileUploadException, IOException;

}