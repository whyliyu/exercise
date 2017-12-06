
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteTest {
//
//    public static void main(String[] args) {
//
//    }


    public static Path root = new Path("file:///d:/test/");

    public static Configuration conf = new Configuration();

    public static MessageType schema = MessageTypeParser.parseMessageType(
        " message people { " +
            "required binary rowkey;" +
            "required binary cf:name;" +
            "required binary cf:age;" +
            "required binary cf:job;" +
            "required int64 timestamp;" +
            " }"
    );

    public static SimpleGroupFactory sfg = new SimpleGroupFactory(schema);

    public static ParquetWriter initWriter(String fileName, Map<String, String> metas)
        throws IOException {


        GroupWriteSupport.setSchema(schema, conf);
        //noinspection unchecked
        return new ParquetWriter(root,
            conf,
            new GroupWriteSupport());
    }


    public static String genRowKey(String format, int i){
        return String.format(format, i);
    }

    public static void testWrite() throws IOException {

        int fileNum = 10;   //num of files constructed
        int fileRecordNum = 50; //record num of each file
        int rowKey = 0;
        for(int i = 0; i < fileNum; ++ i ) {

            Map<String, String> metas = new HashMap<>();

            //            metas.put(HConstants.START_KEY, genRowKey("%10d", rowKey + 1));
            //            metas.put(HConstants.END_KEY, genRowKey("%10d", rowKey + fileRecordNum));

            ParquetWriter<Group> writer = initWriter("pfile/scanner_test_file" + i, metas);

            for (int j = 0;  j < fileRecordNum; ++j) {
                rowKey ++;
                Group group = sfg.newGroup().append("rowkey", genRowKey("%10d", rowKey))
                    .append("cf:name", "wangxiaoyi" + rowKey)
                    .append("cf:age", String.format("%10d", rowKey))
                    .append("cf:job", "student")
                    .append("timestamp", System.currentTimeMillis());
                writer.write(group);
            }

            writer.close();
        }
    }

    public static void readTest() throws IOException {

        ParquetReader<Group> reader = new ParquetReader<Group>(new Path("file:///d:/test1/dump_0"),new GroupReadSupport());
        Group group;
        while((group = reader.read() ) != null) {
            System.out.println("group:" + group);
        }
        reader.close();
    }

    public static void main(String []args) throws IOException{
//        readTest();
        for(int i = 5; i <= 20; i ++) {
            FileOutputStream fileOutputStream = new FileOutputStream("d:/binfile/b" + i);
            fileOutputStream.write(i);
            fileOutputStream.close();
        }

    }
}
