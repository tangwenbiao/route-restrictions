package com.route.client.load;

import com.route.client.core.RuleManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author: TangFenQi
 * @description: 远程加载
 * @date：2020/1/2 16:25
 */
public class RemoteRuleLoader implements RuleLoader {

  private ExecutorService executorService;

  private ThreadFactory threadFactory;

  private RuleManager ruleManager;

  public RemoteRuleLoader() {
    threadFactory = RouteThreadFactory.create("rule", true);
    executorService = Executors.newSingleThreadExecutor(threadFactory);
    //加载
    load();
  }

  /**
   * 加载规则
   */
  @Override
  public void load() {

  }

  private void poll(){
  }
}
