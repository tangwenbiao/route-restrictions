package com.route.client.load;

import com.route.client.core.ClientConfig;
import com.route.client.core.RuleManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: TangFenQi
 * @description: 远程加载
 * @date：2020/1/2 16:25
 */
public class RemoteRuleLoader implements RuleLoader {

  private ScheduledExecutorService executorService;

  private ThreadFactory threadFactory;

  private RuleManager ruleManager;

  private RemoteRepositoryResolver remoteRepositoryResolver;

  public RemoteRuleLoader(RuleManager ruleManager, ClientConfig clientConfig) {
    this.ruleManager = ruleManager;
    threadFactory = RouteThreadFactory.create("rule", true);
    executorService = Executors.newScheduledThreadPool(1, threadFactory);
    remoteRepositoryResolver = new RemoteRepositoryResolver(clientConfig, ruleManager);
    //加载
    load();
    //定时轮询
    executorService.schedule(() -> remoteRepositoryResolver.sync(), 5, TimeUnit.SECONDS);
  }

  /**
   * 加载规则
   */
  @Override
  public void load() {
    remoteRepositoryResolver.sync();
  }

  private void poll() {
  }
}
