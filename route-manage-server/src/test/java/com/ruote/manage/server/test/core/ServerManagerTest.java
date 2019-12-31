package com.ruote.manage.server.test.core;

import com.route.manage.server.ManagerServerApplication;
import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/31 17:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerManagerTest {

  @Autowired
  private ServerManager serverManager;

  @Test
  public void testRootNode() {
    Map<String, List<ProviderConfig>> providerMap = serverManager.getProviderMap();
    System.out.println();
  }


}
