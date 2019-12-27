package com.sofa.client.core.rule;

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.sofa.client.core.ShieldingConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: IP to Ip 规则信息
 * @date：2019/12/23 18:41
 */
public class IPRuleInfo extends AbstractRuleInfo {

  /**
   * 需要屏蔽的IP列表
   */
  private HashSet<String> serverShieldingIPTable;

  /**
   * 对上述屏蔽IP无效的Client端IP地址
   */
  private HashSet<String> prerogativeClientIPTable;

  public IPRuleInfo(int groupId, HashSet<String> serverShieldingIPTable,
      HashSet<String> prerogativeClientIPTable, HashSet<String> userIdTable) {
    super(groupId, RuleType.IP, userIdTable);
    this.serverShieldingIPTable = serverShieldingIPTable;
    this.prerogativeClientIPTable = prerogativeClientIPTable;
  }

  /* @ApolloJsonValue("${server.shielding.ip.table}")
  public void setServerShieldingIPTable(HashSet<String> serverShieldingIPTable) {
    ShieldingConfig.serverShieldingIPTable = serverShieldingIPTable;
  }

  @ApolloJsonValue("${client.prerogative.ip.table}")
  public void setPrerogativeClientIPTable(HashSet<String> prerogativeClientIPTable){
    ShieldingConfig.prerogativeClientIPTable=prerogativeClientIPTable;
  }
*/

  /**
   * 是否是特权ClientIP（特权IP意味着可以访问屏蔽掉的ServerIP）
   *
   * @param hostIP 本机地址
   * @return 是否是特权
   */
  public boolean isPrerogative(String hostIP) {
    if (StringUtils.isEmpty(hostIP)) {
      throw new IllegalArgumentException("本机地址为空！无法验证是否是特权IP");
    }
    return prerogativeClientIPTable.contains(hostIP);
  }

  /**
   * 是否是被屏蔽的ServerIP，如果是，则client无法访问该IP
   *
   * @param serverIP 服务端IP
   * @return 是否是被屏蔽的IP
   */
  public boolean isShielding(String serverIP) {
    if (StringUtils.isEmpty(serverIP)) {
      throw new IllegalArgumentException("服务器地址为空！无法验证是否是屏蔽IP");
    }
    return serverShieldingIPTable.contains(serverIP);
  }

  /**
   * 获取没被屏蔽的服务提供方
   *
   * @param providerInfos 原始服务提供方
   * @return 没被屏蔽的服务提供方
   */
  @Override
  public List<ProviderInfo> getNormal(List<ProviderInfo> providerInfos) {
    if (CollectionUtils.isEmpty(providerInfos)) {
      return new ArrayList<>();
    }
    List<ProviderInfo> normalProvider = providerInfos.stream()
        .filter(p -> !isShielding(p.getHost())).collect(
            Collectors.toList());
    return normalProvider;
  }

  /**
   * 获取屏蔽的服务提供方
   *
   * @param providerInfos 原始服务提供方
   * @return 屏蔽的服务提供方
   */
  @Override
  public List<ProviderInfo> getShielding(List<ProviderInfo> providerInfos) {
    if (CollectionUtils.isEmpty(providerInfos)) {
      return new ArrayList<>();
    }
    List<ProviderInfo> shieldingProvider = providerInfos.stream()
        .filter(p -> isShielding(p.getHost())).collect(
            Collectors.toList());
    return shieldingProvider;
  }
}
