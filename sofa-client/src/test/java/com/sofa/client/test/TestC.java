package com.sofa.client.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.sofa.client.ClientApplication;
import com.sofa.client.TestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/16 17:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class TestC {

  @Autowired
  private TestClient testClient;

  @ApolloConfig
  private Config config;

  @Test
  public void test() {


  }

}
