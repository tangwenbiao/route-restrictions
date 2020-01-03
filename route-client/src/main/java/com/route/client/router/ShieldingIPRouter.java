package com.route.client.router;

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.Router;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.google.gson.Gson;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/18 17:29
 */
@Extension(value = "test")
@AutoActive(consumerSide = true)
@Slf4j
public class ShieldingIPRouter extends Router {

  private Gson gson = new Gson();

  /**
   * 筛选Provider
   *
   * @param request 本次调用（可以得到类名，方法名，方法参数，参数值等）
   * @param providerInfos providers（<b>当前可用</b>的服务Provider列表）
   * @return 路由匹配的服务Provider列表
   */
  @Override
  public List<ProviderInfo> route(SofaRequest request, List<ProviderInfo> providerInfos) {
    return providerInfos;
  }
}
