package com.route.manage.server.zk.listener;

import com.route.manage.server.core.ProviderConfig;
import com.route.manage.server.core.ServerManager;
import com.route.manage.server.core.ServiceConfig;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @author: TangFenQi
 * @description: 服务监听器
 * @date：2019/12/31 16:34
 */
public class ServiceListener implements PathChildrenCacheListener {

  private ServerManager serverManager;

  public ServiceListener(ServerManager serverManager) {
    this.serverManager = serverManager;
  }

  @Override
  public void childEvent(CuratorFramework curatorFramework,
      PathChildrenCacheEvent event) throws Exception {
    switch (event.getType()) {
      case CHILD_ADDED:
      case CHILD_UPDATED:
      case CHILD_REMOVED:
      default:
        return;
    }
  }
}
