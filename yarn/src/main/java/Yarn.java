import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.exceptions.YarnException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liyuhang1 on 2018/3/20.
 */
public class Yarn {

    public static void main(String[] args) throws IOException, YarnException {
        YarnClient yarnClient = YarnClient.createYarnClient();
        YarnClientApplication application = yarnClient.createApplication();
        GetNewApplicationResponse resp = application.getNewApplicationResponse();

        ApplicationId id = resp.getApplicationId();
        Resource resource = resp.getMaximumResourceCapability();
        resource.getVirtualCores();
        resource.getMemory();

        ApplicationSubmissionContext submissionContext = application.getApplicationSubmissionContext();
        submissionContext.getApplicationId();
        submissionContext.setApplicationName("app name");
        submissionContext.setKeepContainersAcrossApplicationAttempts(true);

        Map<String,LocalResource> resources = new HashMap<>();
//        LocalResource localResource = LocalResource.newInstance();
//        submissionContext.setResource();

    }
}
