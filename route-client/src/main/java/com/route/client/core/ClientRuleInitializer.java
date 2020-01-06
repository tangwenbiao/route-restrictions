package com.route.client.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: TangFenQi
 * @description: 客户端规则初始化器
 * @date：2020/1/6 16:12
 */
public class ClientRuleInitializer implements
    ApplicationContextInitializer<ConfigurableApplicationContext> {

  private static ClientConfig CLIENT_CONFIG;

  public static ClientConfig getClientConfig() {
    return CLIENT_CONFIG;
  }

  @Override
  public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
    //加载属性信息
    loadClientConfig(configurableApplicationContext.getEnvironment());
  }

  private void loadClientConfig(ConfigurableEnvironment environment) {
    String address = environment.getProperty(ClientConstants.ROUTE_CONTROL_ADDRESS);
    CLIENT_CONFIG = new ClientConfig();
    CLIENT_CONFIG.setAddress(address);
  }
}
