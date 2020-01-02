package com.ruote.manage.server.test.core;

import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.core.ServiceConfig;
import com.ruote.manage.server.test.TestApplication;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 11:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ProviderMapTest {

  @Autowired
  private ServerManager serverManager;
/*
  @Test
  public void addInServiceTest() {
    String serviceName = "com.sofa.server.ITestServer";
    //当map为空时 添加
    ServiceConfig serviceConfig = ServiceConfig.builder().name(serviceName).build();
    serverManager.addService(serviceConfig);
    Assert.assertEquals(serverManager.getProviderMap().size(), 1);
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : serverManager.getProviderMap()
        .entrySet()) {
      List<ProviderConfig> value = entry.getValue();
      Assert.assertEquals(value.size(), 0);
    }
    //当map有服务时 添加
    String serviceName2 = "com.sofa.server.ITestServer2";
    ServiceConfig serviceConfig2 = ServiceConfig.builder().name(serviceName2).build();
    serverManager.addService(serviceConfig2);
    Assert.assertEquals(serverManager.getProviderMap().size(), 2);
    List<ProviderConfig> providerConfigList = serverManager.get(serviceConfig2);
    Assert.assertEquals(providerConfigList.size(), 1);

  }

  @Test
  public void addInProviderTest() {
    String serviceName = "com.sofa.server.ITestServer";
    //当map为空时 添加
    ServiceConfig serviceConfig = ServiceConfig.builder().name(serviceName).build();
    serverManager.addService(serviceConfig);
    String ip = "10.2.4.1";
    int port = 8800;
    ProviderConfig providerConfig = ProviderConfig.builder()
        .ip(ip).port(port).build();
    serverManager.addProvider(serviceConfig, providerConfig);

    List<ProviderConfig> providerConfigList = serverManager.get(serviceConfig);
    Assert.assertEquals(providerConfigList.size(), 1);
    Assert.assertEquals(providerConfigList.get(0).getPort(), port);
    Assert.assertEquals(providerConfigList.get(0).getIp(), ip);
  }*/


}
