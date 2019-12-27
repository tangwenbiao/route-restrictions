package com.sofa.client.core.load;

import com.sofa.client.core.RuleManager;

/**
 * @author: TangFenQi
 * @description: 规则加载器, 目前都是从apollo进行加载，（结合apollo创建对应模块）
 * @date：2019/12/23 18:49
 */
public class ApolloRuleLoader implements RuleLoader {

  private RuleManager ruleManager;

  public ApolloRuleLoader(RuleManager ruleManager) {
    this.ruleManager = ruleManager;
  }

  /**
   * 加载规则
   */
  @Override
  public void load() {

  }
}
