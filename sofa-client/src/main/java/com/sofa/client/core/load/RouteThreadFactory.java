package com.sofa.client.core.load;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 16:36
 */
@Slf4j
public class RouteThreadFactory implements ThreadFactory {

  private static String rootName = "route";

  private static ThreadGroup threadGroup = new ThreadGroup(rootName);

  private String typeName;

  private AtomicInteger number = new AtomicInteger(1);

  private boolean daemon;

  @Override
  public Thread newThread(Runnable r) {
    String name = rootName + "-" + typeName + "-" + number.getAndAdd(1);
    Thread thread = new Thread(threadGroup, r, name);
    thread.setDaemon(daemon);
    if (thread.getPriority() != Thread.NORM_PRIORITY) {
      thread.setPriority(Thread.NORM_PRIORITY);
    }
    return thread;
  }

  public static ThreadFactory create(String typeName, boolean daemon) {
    return new RouteThreadFactory(typeName, daemon);
  }

  private RouteThreadFactory(String typeName, boolean daemon) {
    this.typeName = typeName;
    this.daemon = daemon;
  }
}
