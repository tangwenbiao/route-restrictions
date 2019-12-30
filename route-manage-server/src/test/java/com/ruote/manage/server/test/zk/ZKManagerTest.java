package com.ruote.manage.server.test.zk;

import com.route.manage.server.ManagerServerApplication;
import com.route.manage.server.zk.ZKManager;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    CuratorFramework curatorClient = zkManager.getCuratorClient();
    List<String> strings = curatorClient.getChildren().forPath("/sofa-rpc");
    System.out.println();
  }

}
