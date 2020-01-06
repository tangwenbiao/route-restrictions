package com.route.client.core;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: TangFenQi
 * @description: 注册器
 * @date：2020/1/3 10:20
 */
public class ClientRuleRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {
    //检测是否开启

    //加载类
    registry.registerBeanDefinition("ruleManager", BeanDefinitionBuilder
        .genericBeanDefinition(RuleManager.class).getBeanDefinition());
  }


}
