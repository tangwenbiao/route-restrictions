package com.sofa.client.controller;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.sofa.server.ITestServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TangFenQi
 * @description: 测试
 * @date：2019/12/23 11:00
 */
@RestController
public class TestController {

  @SofaReference(interfaceType = ITestServer.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
  private ITestServer testServer;

  @RequestMapping(value = "test", method = RequestMethod.GET)
  public void test(@RequestParam String test) {
    testServer.say(test);
  }

}
