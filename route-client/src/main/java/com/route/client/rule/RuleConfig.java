package com.route.client.rule;

/**
 * @author: TangFenQi
 * @description: 规则信息
 * @date：2019/12/23 16:13
 */
public interface RuleConfig {

    /**
     * 规则信息是否为 ruleType类型
      * @param ruleType 规则类型
     * @return true 是该规则类型，false 不是该规则类型
     */
  boolean isType(RuleType ruleType);

  /**
   * 验证该用户是否适用该规则
   * @param userId 用户编号
   * @return true 适用 false 不适用
   */
  boolean isValid(String userId);

}
