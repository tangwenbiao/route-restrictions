package com.route.client.rule;

import com.alipay.sofa.rpc.client.ProviderInfo;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: 所有规则信息应该具有的属性与功能
 * @date：2019/12/23 18:36
 */
public abstract class AbstractRuleInfo implements RuleConfig {

  /**
   * 规则类型
   */
  @Getter
  private RuleType ruleType;

  @Getter
  private int groupId;

  /**
   * 对上述屏蔽IP无效的用户编号
   */
  @Getter
  private HashSet<String> userTable;

  public AbstractRuleInfo(int groupId, RuleType ruleType, HashSet<String> userTable) {
    this.ruleType = ruleType;
    this.groupId = groupId;
    this.userTable = userTable;
  }

  @Override
  public boolean isType(RuleType ruleType) {
    if (ruleType == null) {
      return false;
    }
    return this.ruleType.equals(ruleType);
  }

  @Override
  public boolean isValid(String userId) {
    if (CollectionUtils.isEmpty(userTable)) {
      return false;
    }
    return userTable.contains(userId);
  }

  /**
   * 获取没被屏蔽的服务提供方
   * @param providerInfos  原始服务提供方
   * @return 没被屏蔽的服务提供方
   */
  public abstract List<ProviderInfo> getNormal(List<ProviderInfo> providerInfos);

  /**
   * 获取屏蔽的服务提供方
   * @param providerInfos 原始服务提供方
   * @return 屏蔽的服务提供方
   */
  public abstract List<ProviderInfo> getShielding(List<ProviderInfo> providerInfos);

}
