package com.ruote.manage.server.test.utils;

import com.route.manage.server.ManagerServerApplication;
import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.utils.AddressUtils;
import com.ruote.manage.server.test.TestApplication;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/31 15:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class AddressUtilsTest {

  @Test
  public void parseAddress() {
    //单个
    String exampleOne = "10.27.55.15:1111";
    Map<String, Integer> stringIntegerMap = AddressUtils.parseAddress(exampleOne);
    Assert.assertEquals(stringIntegerMap.size(), 1);
    for (Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
      Assert.assertEquals(entry.getKey(), "10.27.55.15");
      Assert.assertEquals(1111, (int) entry.getValue());
    }
    //多个
    String exampleTwo = "10.27.55.15:1111,10.27.55.16:2222";
    stringIntegerMap = AddressUtils.parseAddress(exampleTwo);
    Assert.assertEquals(stringIntegerMap.size(), 2);
    Assert.assertTrue(stringIntegerMap.containsKey("10.27.55.15"));
    Assert.assertTrue(stringIntegerMap.containsKey("10.27.55.16"));
    Assert.assertEquals((int) stringIntegerMap.get("10.27.55.15"), 1111);
    Assert.assertEquals((int) stringIntegerMap.get("10.27.55.16"), 2222);
  }

  @Test
  public void parseService() {
    String serviceName = "com.sofa.server.ITestServer";
    String path = "/sofa-rpc/" + serviceName + "/providers/";
    String service = AddressUtils.parseService(path);
    Assert.assertEquals(service, serviceName);
  }

  @Test
  public void parseProvider() throws UnsupportedEncodingException {
    String serviceName = "com.sofa.server.ITestServer";
    String ip = "10.28.2.5";
    int port = 8800;
    String url = "bolt://"
        + ip
        + ":"
        + port
        + "?"
        + "test";
    String builder = "/sofa-rpc/"
        + serviceName
        + "/providers/"
        + URLEncoder.encode(url, "utf-8");
    ProviderConfig providerConfig = AddressUtils.parseProvider(builder, serviceName);
    Assert.assertEquals(providerConfig.getIp(), ip);
    Assert.assertEquals(providerConfig.getPort(), port);
  }

}
