package com.route.client.rule;

import com.alipay.sofa.rpc.client.ProviderInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: 权重规则设置
 * @date：2019/12/23 18:43
 */
public class WeightRuleInfo extends AbstractRuleInfo {

  /**
   * 权重
   */
  private BigDecimal weight;

  /**
   * 受影响的ip列表
   */
  private HashSet<String> effectiveIPTable;

  public WeightRuleInfo(int groupId, BigDecimal weight, HashSet<String> effectiveIPTable,
      HashSet<String> userIdTable) {
    super(groupId, RuleType.WEIGHT, userIdTable);
    this.weight = weight;
    this.effectiveIPTable = effectiveIPTable;
  }

  public BigDecimal getWeight(String ip) {
    if (effectiveIPTable.contains(ip)) {
      return weight;
    }
    return null;
  }

  public boolean isExist(String ip) {
    return effectiveIPTable.contains(ip);
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
    List<ProviderInfo> collect = providerInfos.stream().filter(p ->
        !isExist(p.getHost())
    ).collect(Collectors.toList());
    return collect;
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
    List<ProviderInfo> collect = providerInfos.stream().filter(p ->
        isExist(p.getHost())
    ).collect(Collectors.toList());
    return collect;
  }
}
