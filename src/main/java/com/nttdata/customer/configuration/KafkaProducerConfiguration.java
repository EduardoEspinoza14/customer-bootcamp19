package com.nttdata.customer.configuration;

import com.nttdata.customer.model.mongo.CustomerMongo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

/**
 * Class KafkaProducerConfiguration.
 */
@Configuration
public class KafkaProducerConfiguration {

  public static final String TOPIC_INSERT = "customer.insert";
  public static final String TOPIC_UPDATE = "customer.update";
  public static final String TOPIC_DELETE = "customer.delete";

  @Value(value = "${kafka.bootstrapAddress:}")
  private String bootstrapAddress;

  public static Flux<SenderResult<Long>> senderCreate(SenderOptions<String,
          CustomerMongo> options, SenderRecord<String, CustomerMongo, Long> record) {
    return KafkaSender.<String, CustomerMongo>create(options).send(Flux.just(record));
  }

  /**
   * Method senderOptions.
   */
  @Bean
  public SenderOptions<String, CustomerMongo> senderOptions() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return SenderOptions.create(props);
  }

  private static SenderRecord<String, CustomerMongo, Long> senderRecord(
          String id,
          CustomerMongo customerMongo,
          String topic,
          Long correlation) {
    return SenderRecord.create(new ProducerRecord<>(topic, id, customerMongo), correlation);
  }

  private static SenderRecord<String, CustomerMongo, Long> senderRecord(
          String id,
          CustomerMongo customerMongo,
          String topic) {
    return senderRecord(id, customerMongo, topic, new Date().getTime());
  }

  private static SenderRecord<String, CustomerMongo, Long> senderRecord(
          CustomerMongo customerMongo,
          String topic) {
    return senderRecord(customerMongo.getId(), customerMongo, topic);
  }

  private static SenderRecord<String, CustomerMongo, Long> senderRecord(String id, String topic) {
    return senderRecord(id, null, topic);
  }

  public static SenderRecord<String, CustomerMongo, Long> insertRecord(
          CustomerMongo customerMongo) {
    return senderRecord(customerMongo, TOPIC_INSERT);
  }

  public static SenderRecord<String, CustomerMongo, Long> updateRecord(
          CustomerMongo customerMongo) {
    return senderRecord(customerMongo, TOPIC_UPDATE);
  }

  public static SenderRecord<String, CustomerMongo, Long> deleteRecord(
          String id) {
    return senderRecord(id, TOPIC_DELETE);
  }

}
