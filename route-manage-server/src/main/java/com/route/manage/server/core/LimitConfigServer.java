package com.route.manage.server.core;

import com.route.manage.server.domain.IPLimitInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description: 限制信息服务管理
 * @date：2020/1/2 15:51
 */
@Component
public class LimitConfigServer {

  private ConcurrentHashMap<Integer, IPLimitInfo> limitMap = new ConcurrentHashMap<>();

  private AtomicInteger limitNumber = new AtomicInteger(0);

  public List<IPLimitInfo> getAll() {
    limitMap = new ConcurrentHashMap<>();
    HashSet<String> ip = new HashSet<>();
    ip.add("10.25.3.3");
    HashSet<String> prerogative = new HashSet<>();
    addIPLimit(ip, prerogative);
    return new ArrayList<>(limitMap.values());
  }

  /**
   * 添加限制信息
   */
  public Integer addIPLimit(HashSet<String> ip, HashSet<String> prerogative) {
    IPLimitInfo ipLimitInfo = new IPLimitInfo();
    ipLimitInfo.setPrerogativeClientIPTable(prerogative);
    ipLimitInfo.setServerShieldingIPTable(ip);
    int number = limitNumber.getAndAdd(1);
    limitMap.put(number, ipLimitInfo);
    return number;
  }

  /**
   * 移除限制信息
   *
   * @param number 规则编号
   */
  public void removeIPLimit(Integer number) {
    limitMap.remove(number);
  }

}
