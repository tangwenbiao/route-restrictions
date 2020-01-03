package com.route.client.load;

import com.google.gson.reflect.TypeToken;
import com.route.client.core.ClientConfig;
import com.route.client.rule.IPRuleInfo;
import com.route.client.utils.HttpRequest;
import com.route.client.utils.HttpResponse;
import com.route.client.utils.HttpUtils;
import com.route.client.utils.URLBuilder;
import java.util.List;

/**
 * @author: TangFenQi
 * @description: 配置信息加载处理器
 * @date：2020/1/3 10:17
 */
public class RemoteRepositoryResolver {

  private ClientConfig clientConfig;

  public RemoteRepositoryResolver(ClientConfig clientConfig) {
    this.clientConfig = clientConfig;
  }

  public void sync() {
    //构建连接信息
    String url = URLBuilder.buildQueryAll(clientConfig);
    //请求
    HttpRequest httpRequest = new HttpRequest();
    httpRequest.setUrl(url);
    HttpResponse<List<IPRuleInfo>> response = HttpUtils
        .get(httpRequest, new TypeToken<List<IPRuleInfo>>() {
        }.getType());
    //写入本地

  }


}
