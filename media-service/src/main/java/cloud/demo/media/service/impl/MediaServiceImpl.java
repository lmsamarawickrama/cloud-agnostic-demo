package cloud.demo.media.service.impl;

import cloud.demo.agnostic.api.CloudMessagingService;
import cloud.demo.agnostic.api.CloudStorageService;
import cloud.demo.media.service.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MediaServiceImpl implements MediaService {
    @Value("${queue.name}")
    private String queueName;

    @Value("${bucket.name}")
    private String bucketName;

    private final CloudStorageService storageService;
    private final CloudMessagingService messagingService;

    public MediaServiceImpl(CloudStorageService storageService, CloudMessagingService messagingService) {
        this.storageService = storageService;
        this.messagingService = messagingService;
    }

    @Override
    public void uploadMedia(String fileName, InputStream fileStream) {
        final String result = storageService.uploadFile(bucketName, fileName, fileStream);
        messagingService.sendMessage(queueName, result);
    }
}
