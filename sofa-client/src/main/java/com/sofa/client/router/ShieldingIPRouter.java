package com.sofa.client.router;

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.Router;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.google.gson.Gson;
import com.sofa.client.core.utils.IPUtils;
import com.sofa.client.core.ShieldingConfig;
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
    log.debug("原始provider列表:{}", gson.toJson(providerInfos));
    //过滤屏蔽IP
    List<ProviderInfo> normalProvider = providerInfos.stream()
        .filter(p -> !ShieldingConfig.isShielding(p.getHost())).collect(
            Collectors.toList());
    //屏蔽IP
    List<ProviderInfo> shieldingProvider = providerInfos.stream()
        .filter(p -> ShieldingConfig.isShielding(p.getHost())).collect(
            Collectors.toList());
    //获取自身IP
    String clientAddress;
    try {
      clientAddress = IPUtils.getLocalHostLANAddress().getHostAddress();
    } catch (UnknownHostException e) {
      log.error("未找到本机IP地址！ex:", e);
      log.debug("返回的provider列表:{}", gson.toJson(normalProvider));
      return normalProvider;
    }
    log.debug("本机地址:{}", clientAddress);
    //client是否具有特权
    if (ShieldingConfig.isPrerogative(clientAddress)) {
      log.debug("返回的provider列表:{}", gson.toJson(shieldingProvider));
      return shieldingProvider;
    }

    //用户验证

    log.debug("返回的provider列表:{}", gson.toJson(normalProvider));
    return normalProvider;

  }
}
