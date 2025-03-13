package cloud.demo.media.service;

import java.io.InputStream;

public interface MediaService {


    void uploadMedia(String fileName, InputStream fileStream);
}
