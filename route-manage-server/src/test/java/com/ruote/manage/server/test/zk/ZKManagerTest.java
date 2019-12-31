package com.ruote.manage.server.test.zk;

import com.route.manage.server.ManagerServerApplication;
import com.route.manage.server.zk.ZKManager;
import java.util.List;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.logging.slf4j.SLF4JLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/30 15:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZKManagerTest {

  @Autowired
  private ZKManager zkManager;

  @Test
  public void test() throws Exception {
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    CuratorFramework curatorClient = CuratorFrameworkFactory.builder()
        .connectString("10.28.33.228:22,10.28.33.240:22")
        .retryPolicy(retryPolicy)
        .build();
    curatorClient.start();

    //List<String> strings = curatorClient.getChildren().forPath("/sofa-rpc");
    System.out.println();
  }

  @Test
  public void testLog(){
    Logger logger= LoggerFactory.getLogger(ZKManagerTest.class);
    logger.info("abc");
  }


}
