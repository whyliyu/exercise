
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Map;

public class CallbackTest {

    //kafka producer config
    private Map<String, Object> kafkaProps = new HashMap<>();

    public static final String DEFAULT_KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    public static final String DEFAULT_VALUE_SERIAIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";

    public static final String bootstrapServers = "10.241.12.50:9092";

    String topic = "testlyh";

    private KafkaProducer<String, byte[]> producer;

    public CallbackTest() {
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "1");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, DEFAULT_KEY_SERIALIZER);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DEFAULT_VALUE_SERIAIZER);
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        kafkaProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        kafkaProps.put("request.timeout.ms", "5000");
        kafkaProps.put("metadata.fetch.timeout.ms", "5000");

        producer = new KafkaProducer(kafkaProps);
    }


    public void write(final String content,String key) {
        ProducerRecord<String, byte[]> record = new ProducerRecord<>(topic, key, content.getBytes());
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {

                if(exception == null)
                    System.out.println("send message :" + content + " successfully");
                else {
                    System.out.println("send message :" + content + " failed");
                    exception.printStackTrace();
                }

            }
        });
    }

    public void flush() {
        producer.flush();
    }

    public static void main(String[] args) {
        CallbackTest callbackTest = new CallbackTest();
        for(int i = 0; i < 100; i++) {
            callbackTest.write("message" + i,"key" + i);
        }
        System.out.println("send end");
        callbackTest.flush();
    }
}
