import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by liyuhang1 on 2018/3/20.
 */
public class MRDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = new Job();
        /**
         * 如果需要多个jar包怎么上传
         */
        job.setJar(null);
        job.setCacheFiles(null);
        job.setCacheArchives(null);
        job.waitForCompletion(true);
    }
}
