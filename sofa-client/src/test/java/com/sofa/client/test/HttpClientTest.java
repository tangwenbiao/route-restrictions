package com.sofa.client.test;

import com.google.gson.reflect.TypeToken;
import com.sofa.client.ClientApplication;
import com.sofa.client.core.rule.IPRuleInfo;
import com.sofa.client.core.utils.HttpRequest;
import com.sofa.client.core.utils.HttpResponse;
import com.sofa.client.core.utils.HttpUtils;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 17:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class HttpClientTest {

  @Test
  public void test() {
    Type type = new TypeToken<List<IPRuleInfo>>() {
    }.getType();
    HttpRequest httpRequest = new HttpRequest();
    httpRequest.setUrl("http://10.28.6.71:9823/limit/getAll");
    HttpResponse<Object> objectHttpResponse = HttpUtils.get(httpRequest, type);
    System.out.println();
  }


}
