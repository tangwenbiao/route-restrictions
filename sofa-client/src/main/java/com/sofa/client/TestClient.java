package com.sofa.client;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.sofa.server.ITestServer;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/16 17:01
 */
@Component
public class TestClient {

  @SofaReference(interfaceType = ITestServer.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
  private ITestServer testServer;

  public void test(String context) {
    testServer.say(context);
  }

}
