package cloud.demo.agnostic.api;

public interface CloudMessagingService {
    void sendMessage(String queueName, String message);
}
