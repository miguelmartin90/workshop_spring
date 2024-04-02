package org.example.consumer;

import org.example.dto.FileMetadataDTO;
import org.example.model.FileMetadata;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.RabbitMessageFuture;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Component
public class ReaderResponseConsumer {

    public FileMetadata fileMetadata = new FileMetadata();
    CompletableFuture<FileMetadataDTO> future;

    @Autowired
    private AmqpAdmin amqpAdmin;
    @RabbitListener(queues = {"validator-response-queue"})
    public void receiverMessage(boolean validLineStatus) throws ParseException {

        fileMetadata.validatedLinesCounter(validLineStatus);

       /* System.out.println(
                "Valid Lines: " + fileMetadata.getValidLines() + '\n' +
                "Invalid Lines: " + fileMetadata.getInvalidLines()
        );*/
        /*if (isQueueEmpty("validator-response-queue") ) {

        }*/
        //return new FileMetadataDTO(fileMetadata.getValidLines(), fileMetadata.getInvalidLines());
    }

    public boolean isQueueEmpty(String queueName) {
        // Obtener información de la cola
        Integer count = (Integer) amqpAdmin.getQueueProperties(queueName).get("QUEUE_MESSAGE_COUNT");
        //amqpAdmin.purgeQueue("validator-response-queue", true);
        return (count == 0);
    }
}
