package com.example.kbeproject.upload;

public interface FileTransferService {

    boolean uploadFile(String localFilePath, String remoteFilePath);

}
