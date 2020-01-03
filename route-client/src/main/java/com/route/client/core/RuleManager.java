package com.route.client.core;

import com.route.client.rule.AbstractRuleInfo;
import com.route.client.rule.RuleType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: 规则管理器
 * @date：2019/12/23 18:49
 */
public class RuleManager {

  /**
   * 根据类型分组
   */
  private ConcurrentHashMap<RuleType, List<AbstractRuleInfo>> ruleMapOfType;

  /**
   * 根据groupId分组
   */
  private ConcurrentHashMap<Integer, AbstractRuleInfo> ruleOfGroupId;

  private List<AbstractRuleInfo> originalRuleList;

  public RuleManager(List<AbstractRuleInfo> ruleInfoList) {
    initRuleMap(ruleInfoList);
  }

  private void initRuleMap(List<AbstractRuleInfo> ruleMap) {
    originalRuleList = ruleMap;
    if (CollectionUtils.isEmpty(ruleMap)) {
      this.ruleMapOfType = new ConcurrentHashMap<>();
      this.ruleOfGroupId = new ConcurrentHashMap<>();
    } else {
      //类型分类
      Map<RuleType, List<AbstractRuleInfo>> collect = ruleMap.stream()
          .collect(Collectors.groupingBy(AbstractRuleInfo::getRuleType));
      this.ruleMapOfType = new ConcurrentHashMap<>(collect);
      //组分类 便于更新
      Map<Integer, AbstractRuleInfo> groupRuleMap = new HashMap<>();
      ruleMap.forEach(r -> {
        groupRuleMap.put(r.getGroupId(), r);
      });
      this.ruleOfGroupId = new ConcurrentHashMap<>(groupRuleMap);
    }

  }

  public List<AbstractRuleInfo> getRule(RuleType ruleType) {
    return ruleMapOfType.get(ruleType);
  }

}
