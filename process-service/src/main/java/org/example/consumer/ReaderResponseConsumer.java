package org.example.consumer;

import org.example.model.FileMetadata;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ReaderResponseConsumer {

    private FileMetadata fileMetadata = new FileMetadata();
    @Autowired
    public ReaderResponseConsumer(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    @RabbitListener(queues = {"validator-response-queue"})
    public void receiverMessage(boolean validLineStatus) throws ParseException {
        fileMetadata.validatedLinesCounter(validLineStatus);
    }

}
