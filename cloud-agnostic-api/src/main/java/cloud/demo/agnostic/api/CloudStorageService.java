package cloud.demo.agnostic.api;

import java.io.InputStream;

public interface CloudStorageService {
    String uploadFile(String bucketName, String fileName, InputStream fileStream);
    InputStream downloadFile(String bucketName, String fileName);
    void deleteFile(String bucketName, String fileName);
}
