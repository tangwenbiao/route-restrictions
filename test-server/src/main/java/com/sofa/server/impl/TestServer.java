package com.sofa.server.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.sofa.server.ITestServer;
import org.springframework.stereotype.Service;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/16 16:55
 */
@Service
@SofaService(interfaceType = ITestServer.class, bindings = {
    @SofaServiceBinding(bindingType = "bolt")})
public class TestServer implements ITestServer {

  @Override
  public void say(String context) {

    System.out.println("local 环境:" + context);
  }
}
