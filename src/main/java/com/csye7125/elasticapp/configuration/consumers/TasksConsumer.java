package com.csye7125.elasticapp.configuration.consumers;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.csye7125.elasticapp.module.tasks.TaskElastic;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class TasksConsumer {

    @Value(value = "${spring.elastic.index.tasks}")
    private String tasksIndex;

    @Autowired
    ElasticsearchClient elasticsearchClient;

    Logger logger = LoggerFactory.getLogger(TasksConsumer.class);

    @KafkaListener(topics = "updateTaskTopic1", groupId = "foo")
    public void listenGroupFoo(
            @Header(KafkaHeaders.OFFSET) String offset,
            @Header(KafkaHeaders.TOPIC) String kafkaTopic,
            String task) throws IOException {
        System.out.println("Received Message in group foo: " + task);

        logger.info("[TasksConsumer][listenGroupFoo] Consuming message with offset:" + offset + " for topic:" + kafkaTopic);
        JSONObject jsonObject = new JSONObject(task);
        TaskElastic taskElastic = new TaskElastic();
        taskElastic.setId(jsonObject.get("id").toString());
        taskElastic.setName(jsonObject.get("name").toString());
        LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");

//        taskElastic.setDueDate(LocalDateTime.parse(jsonObject.get("dueDate").toString()));
        taskElastic.setPriority(jsonObject.get("priority").toString());
        taskElastic.setSummary(jsonObject.get("summary").toString());
        taskElastic.setState(jsonObject.get("state").toString());
//        taskElastic.setCreatedAt(LocalDateTime.parse(jsonObject.get("createdAt").toString()));
//        taskElastic.setUpdatedAt(LocalDateTime.parse(jsonObject.get("updatedAt").toString()));
        try{
            IndexResponse response = elasticsearchClient.index(i -> i
                    .index(tasksIndex)
                    .id(jsonObject.get("id").toString())
                    .document(taskElastic)
            );
            System.out.println(response.toString());
            System.out.println(response.result().jsonValue());
            if(response.result().name().equals("Created")){
                logger.info("[TasksConsumer][listenGroupFoo] new StringBuilder(\"Document has been successfully created.\").toString()");
            }else if(response.result().name().equals("Updated")){
                logger.info("[TasksConsumer][listenGroupFoo] new StringBuilder(\"Document has been successfully updated.\").toString()");
                System.out.println(new StringBuilder("Document has been successfully updated.").toString());
            }
        } catch(Exception e){
            e.printStackTrace();
            logger.info("[TasksConsumer][listenGroupFoo] error in indexing task" + e.getMessage());

        }


    }

}
