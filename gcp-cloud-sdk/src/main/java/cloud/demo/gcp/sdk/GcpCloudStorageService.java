package cloud.demo.gcp.sdk;

import cloud.demo.agnostic.api.CloudStorageService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class GcpCloudStorageService implements CloudStorageService {

    private final Storage storage;

    public GcpCloudStorageService(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String uploadFile(String bucketName, String fileName, InputStream fileStream) {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, fileStream);
        return "File uploaded to GCP Cloud Storage: " + fileName;
    }

    @Override
    public InputStream downloadFile(String bucketName, String fileName) {
        return new ByteArrayInputStream(storage.get(bucketName, fileName).getContent());
    }

    @Override
    public void deleteFile(String bucketName, String fileName) {
        storage.delete(bucketName, fileName);
    }
}