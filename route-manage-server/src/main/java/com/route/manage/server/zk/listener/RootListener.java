package com.route.manage.server.zk.listener;

import com.route.manage.server.constants.RouteManageConstants;
import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.core.ServiceConfig;
import com.route.manage.server.utils.AddressUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.util.StringUtils;

/**
 * @author: TangFenQi
 * @description: 根节点监听器
 * @date：2019/12/31 16:32
 */
public class RootListener implements PathChildrenCacheListener {

  private ServerManager serverManager;

  private ServiceListener serviceListener;

  public RootListener(ServerManager serverManager) {
    this.serverManager = serverManager;
    this.serviceListener = new ServiceListener(serverManager);
  }

  @Override
  public void childEvent(CuratorFramework curatorFramework,
      PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
    switch (pathChildrenCacheEvent.getType()) {
      case CHILD_ADDED:
        String addPath = pathChildrenCacheEvent.getData().getPath();
        String addName = AddressUtils.parseService(addPath);
        if (StringUtils.isEmpty(addName)) {
          return;
        }
        ServiceConfig addServiceConfig = new ServiceConfig();
        addServiceConfig.setName(addName);
        serverManager.addService(addServiceConfig);
        //建立Provider的监听
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,
            RouteManageConstants.ZK_SOFA_SERVER_PREFIX + addName, true);
        pathChildrenCache.getListenable().addListener(serviceListener);
        pathChildrenCache.start();
        break;
      case CHILD_UPDATED:
        String updatePath = pathChildrenCacheEvent.getData().getPath();
        String updateName = AddressUtils.parseService(updatePath);
        if (StringUtils.isEmpty(updateName)) {
          return;
        }
        ServiceConfig updateServiceConfig = new ServiceConfig();
        updateServiceConfig.setName(updateName);
        serverManager.addService(updateServiceConfig);
        break;
      case CHILD_REMOVED:
        String removePath = pathChildrenCacheEvent.getData().getPath();
        String removeName = AddressUtils.parseService(removePath);
        if (StringUtils.isEmpty(removeName)) {
          return;
        }
        ServiceConfig removeConfig = new ServiceConfig();
        removeConfig.setName(removeName);
        serverManager.removeService(removeConfig);
      default:
        return;
    }
  }


}
