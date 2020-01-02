package com.route.manage.server.zk.listener;

import com.route.manage.server.constants.RouteManageConstants;
import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.core.ServiceConfig;
import com.route.manage.server.utils.AddressUtils;
import com.route.manage.server.utils.BuildPathUtils;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;

/**
 * @author: TangFenQi
 * @description: 服务监听器
 * @date：2019/12/31 16:34
 */
public class ServiceListener implements PathChildrenCacheListener {

  private ServerManager serverManager;

  private ProviderListener providerListener;

  public ServiceListener(ServerManager serverManager) {
    this.serverManager = serverManager;
    this.providerListener = new ProviderListener(serverManager);
  }

  @Override
  public void childEvent(CuratorFramework curatorFramework,
      PathChildrenCacheEvent event) throws Exception {
    switch (event.getType()) {
      case CHILD_ADDED:
        String path = event.getData().getPath();

        if (path.endsWith(RouteManageConstants.ZK_SOFA_SERVER_PROVIDER)) {
          String serviceName = AddressUtils.parseService(path);
          ServiceConfig serviceConfig = new ServiceConfig();
          serviceConfig.setName(serviceName);
          serverManager.addService(serviceConfig);
          //添加下级监听
          PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,
              BuildPathUtils.buildService(serviceName), true);
          pathChildrenCache.getListenable().addListener(providerListener);
          pathChildrenCache.start();
          break;
        }
      case CHILD_UPDATED:
      case CHILD_REMOVED:
        String removePath = event.getData().getPath();
        String removeService = AddressUtils.parseService(removePath);
        ServiceConfig removeConfig = ServiceConfig.builder().name(removeService).build();
        //移除节点
        serverManager.removeService(removeConfig);
      default:
        return;
    }
  }
}
