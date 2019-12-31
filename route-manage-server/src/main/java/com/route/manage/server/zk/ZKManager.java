package com.route.manage.server.zk;

import com.route.manage.server.constants.RouteManageConstants;
import com.route.manage.server.core.RegistryConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.utils.AddressUtils;
import com.route.manage.server.zk.listener.RootListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description: zk管理器
 * @date：2019/12/30 13:52
 */
@Slf4j
public class ZKManager {

  @Getter
  private CuratorFramework curatorClient;

  private RootListener rootListener;

  public ZKManager(RegistryConfig registryConfig, ServerManager serverManager) {
    rootListener = new RootListener(serverManager);
    //创建client连接zk
    buildClient(registryConfig);
    //监听对应事件
    subscribe(RouteManageConstants.ZK_SOFA_ROOT, rootListener);
  }

  /**
   * 订阅zk 的节点信息
   *
   * @param path 节点路径
   * @param listener 监听器
   */
  public void subscribe(String path, PathChildrenCacheListener listener) {
    try {
      PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorClient,
          RouteManageConstants.ZK_SEPARATOR + path, true);
      pathChildrenCache.start(StartMode.NORMAL);
      pathChildrenCache.getListenable().addListener(listener);
    } catch (Exception e) {
      log.error("create node :{}  is failed,ex:", path, e);
    }
  }

  private void buildClient(RegistryConfig registryConfig) {
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    curatorClient = CuratorFrameworkFactory.builder()
        .retryPolicy(retryPolicy)
        .connectString(registryConfig.getAddress())
        .build();
    if (curatorClient == null) {
      log.error("started curator client is fail!!");
      throw new RuntimeException("启动curator客户端失败!");
    }
    if (curatorClient.getState() == CuratorFrameworkState.STARTED) {
      return;
    }
    try {
      curatorClient.start();
      log.info("curator client start success!! address:{}", registryConfig.getAddress());
    } catch (Exception ex) {
      log.error("curator client start failed! ex:", ex);
      throw new RuntimeException("启动curator客户端失败!");
    }
  }

}
