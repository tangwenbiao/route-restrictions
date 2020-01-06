package com.route.client.core;

import com.route.client.load.RemoteRuleLoader;
import com.route.client.rule.AbstractRuleInfo;
import com.route.client.rule.IPRuleInfo;
import com.route.client.rule.RuleType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: 规则管理器
 * @date：2019/12/23 18:49
 */
public class RuleManager implements SmartInitializingSingleton {

  private RemoteRuleLoader remoteRuleLoader;

  /**
   * 根据类型分组
   */
  private ConcurrentHashMap<RuleType, List<IPRuleInfo>> ruleMapOfType;

  /**
   * 根据groupId分组
   */
  private ConcurrentHashMap<Integer, IPRuleInfo> ruleOfGroupId;

  private List<IPRuleInfo> originalRuleList;

  private void initRuleMap(List<IPRuleInfo> ruleMap) {
    originalRuleList = ruleMap;
    if (CollectionUtils.isEmpty(ruleMap)) {
      this.ruleMapOfType = new ConcurrentHashMap<>();
      this.ruleOfGroupId = new ConcurrentHashMap<>();
    } else {
      //类型分类
      Map<RuleType, List<IPRuleInfo>> collect = ruleMap.stream()
          .collect(Collectors.groupingBy(AbstractRuleInfo::getRuleType));
      this.ruleMapOfType = new ConcurrentHashMap<>(collect);
      //组分类 便于更新
      Map<Integer, IPRuleInfo> groupRuleMap = new HashMap<>();
      ruleMap.forEach(r -> {
        groupRuleMap.put(r.getGroupId(), r);
      });
      this.ruleOfGroupId = new ConcurrentHashMap<>(groupRuleMap);
    }

  }

  public List<IPRuleInfo> getRule(RuleType ruleType) {
    return ruleMapOfType.get(ruleType);
  }

  public void update(List<IPRuleInfo> ruleInfoList) {
    initRuleMap(ruleInfoList);
  }

  @Override
  public void afterSingletonsInstantiated() {
    //初始化远程加载器
    remoteRuleLoader = new RemoteRuleLoader(this, ClientRuleInitializer.getClientConfig());
  }
}
