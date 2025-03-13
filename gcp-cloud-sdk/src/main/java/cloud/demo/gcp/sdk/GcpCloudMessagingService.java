package cloud.demo.gcp.sdk;

import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;

import com.google.cloud.pubsub.v1.Publisher;

import cloud.demo.agnostic.api.CloudMessagingService;

public class GcpCloudMessagingService implements CloudMessagingService {

    
    @Override
    public void sendMessage(String topicName, String message) {
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(message)).build();
        try {
            Publisher.newBuilder(topicName).build().publish(pubsubMessage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send message to GCP Pub/Sub", e);
        }
    }
}