package classroom.store.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class EventSenderService {
    private final KafkaTemplate<String, String> kafkaTemplate;


    public EventSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send() throws ExecutionException, InterruptedException {
        kafkaTemplate.send("topic", "data").get();
    }
}
