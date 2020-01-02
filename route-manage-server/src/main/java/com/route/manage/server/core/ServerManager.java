package com.route.manage.server.core;

import com.route.manage.server.constants.RouteManageConstants;
import com.route.manage.server.utils.AddressUtils;
import com.route.manage.server.zk.ZKManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description: 配置服务管理器
 * @date：2019/12/31 15:54
 */
@Component
public class ServerManager implements ApplicationListener {

  private ZKManager zkManager;

  @Getter
  private Map<ServiceConfig, List<ProviderConfig>> providerMap = new ConcurrentHashMap<>();

  @Autowired
  private Environment environment;

  private RegistryConfig registryConfig;

  @Override
  public void onApplicationEvent(ApplicationEvent applicationEvent) {
    if (applicationEvent instanceof ContextRefreshedEvent) {
      String address = environment.getProperty(RouteManageConstants.SOFA_ZK_ADDRESS);
      registryConfig = new RegistryConfig();
      registryConfig.setAddress(address);
      registryConfig.setIPAndPortMap(AddressUtils.parseAddress(address));
      zkManager = new ZKManager(registryConfig, this);
    }
  }

  /**
   * 根据服务信息 返回提供者信息集合
   *
   * @param serviceConfig 服务信息
   * @return 提供者信息
   */
  public List<ProviderConfig> get(ServiceConfig serviceConfig) {
    List<ProviderConfig> providerConfigList = new ArrayList<>();
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : providerMap.entrySet()) {
      if (entry.getKey().getName().equals(serviceConfig.getName())) {
        return entry.getValue();
      }
    }
    return providerConfigList;
  }

  /**
   * 添加服务信息
   */
  public void addService(ServiceConfig serviceConfig) {
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : providerMap.entrySet()) {
      if (entry.getKey().getName().equals(serviceConfig.getName())) {
        return;
      }
    }
    providerMap.put(serviceConfig, new ArrayList<>());
  }

  /**
   * 移除服务信息
   */
  public void removeService(ServiceConfig serviceConfig) {
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : providerMap.entrySet()) {
      if (serviceConfig.getName().equals(entry.getKey().getName())) {
        providerMap.remove(entry.getKey());
      }
    }
  }

  /**
   * 添加服务提供者
   *
   * @param serviceConfig 服务信息
   * @param providerConfig 提供者信息
   */
  public void addProvider(ServiceConfig serviceConfig, ProviderConfig providerConfig) {
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : providerMap.entrySet()) {
      if (entry.getKey().getName().equals(serviceConfig.getName())) {
        addProvider(entry.getValue(), providerConfig);
        return;
      }
    }
    List<ProviderConfig> providerConfigList = new ArrayList<>();
    providerConfigList.add(providerConfig);
    providerMap.put(serviceConfig, providerConfigList);
  }

  private void addProvider(List<ProviderConfig> value, ProviderConfig providerConfig) {
    for (ProviderConfig config : value) {
      if (config.getIp().equals(providerConfig.getIp()) && config.getPort() == providerConfig
          .getPort()) {
        return;
      }
    }
    value.add(providerConfig);
  }

  /**
   * 移除provider信息
   */
  public void removeProvider(ServiceConfig serviceConfig, ProviderConfig providerConfig) {
    for (Entry<ServiceConfig, List<ProviderConfig>> entry : providerMap.entrySet()) {
      if (entry.getKey().getName().equals(serviceConfig.getName())) {
        for (ProviderConfig config : entry.getValue()) {
          if (config.getPort() == providerConfig.getPort() && config.getIp()
              .equals(providerConfig.getIp())) {
            entry.getValue().remove(config);
          }
        }
      }
    }
  }


}
