package com.route.manage.server.zk.listener;

import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.core.ServiceConfig;
import com.route.manage.server.utils.AddressUtils;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @author: TangFenQi
 * @description: 服务提供者监听器
 * @date：2019/12/31 16:34
 */
public class ProviderListener implements PathChildrenCacheListener {

  private ServerManager serverManager;

  public ProviderListener(ServerManager serverManager) {
    this.serverManager = serverManager;
  }

  @Override
  public void childEvent(CuratorFramework curatorFramework,
      PathChildrenCacheEvent event) throws Exception {
    switch (event.getType()) {
      case CHILD_ADDED:
        String path = event.getData().getPath();
        String service = AddressUtils.parseService(path);
        ProviderConfig providerConfig = AddressUtils.parseProvider(path, service);
        ServiceConfig serviceConfig = ServiceConfig.builder().name(service).build();
        //添加节点
        serverManager.addProvider(serviceConfig, providerConfig);
        break;
      case CHILD_UPDATED:
      case CHILD_REMOVED:
        String removePath = event.getData().getPath();
        String removeServiceName = AddressUtils.parseService(removePath);
        ProviderConfig removeProvider = AddressUtils.parseProvider(removePath, removeServiceName);
        ServiceConfig removeService = ServiceConfig.builder().name(removeServiceName).build();
        //移除
        serverManager.removeProvider(removeService, removeProvider);
      default:
        return;
    }
  }
}
