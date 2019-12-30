package com.route.manage.server.zk;

import lombok.Getter;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description: zk管理器
 * @date：2019/12/30 13:52
 */
@Component
public class ZKManager implements InitializingBean {

  @Getter
  private CuratorFramework curatorClient;


  @Override
  public void afterPropertiesSet() throws Exception {
    RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);
    curatorClient = CuratorFrameworkFactory.builder()
        .connectString("10.28.18.82")
        .retryPolicy(retryPolicy)
        .build();
    curatorClient.start();
  }

}
