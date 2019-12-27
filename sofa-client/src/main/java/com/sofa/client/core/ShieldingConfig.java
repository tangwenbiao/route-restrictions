package com.sofa.client.core;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: TangFenQi
 * @description: 屏蔽相关信息的配置中心
 * @date：2019/12/20 17:30
 */
@Component
public class ShieldingConfig {

  /**
   * 需要屏蔽的IP列表
   */
  private static HashSet<String> serverShieldingIPTable = new HashSet<>();

  /**
   * 对上述屏蔽IP无效的Client端IP地址
   */
  private static HashSet<String> prerogativeClientIPTable = new HashSet<>();

  /**
   * 对上述屏蔽IP无效的用户编号
   */
  private static HashSet<String> userTable = new HashSet<>();

  @Value("${server.shielding.ip.table}")
  private String ip;

  @ApolloJsonValue("${server.shielding.ip.table}")
  public void setServerShieldingIPTable(HashSet<String> serverShieldingIPTable) {
    ShieldingConfig.serverShieldingIPTable = serverShieldingIPTable;
  }

  @ApolloJsonValue("${client.prerogative.ip.table}")
  public void setPrerogativeClientIPTable(HashSet<String> prerogativeClientIPTable) {
    ShieldingConfig.prerogativeClientIPTable = prerogativeClientIPTable;
  }

  /**
   * 是否是特权ClientIP（特权IP意味着可以访问屏蔽掉的ServerIP）
   *
   * @param hostIP 本机地址
   * @return 是否是特权
   */
  public static boolean isPrerogative(String hostIP) {
    if (StringUtils.isEmpty(hostIP)) {
      throw new IllegalArgumentException("本机地址为空！无法验证是否是特权IP");
    }
    return prerogativeClientIPTable.contains(hostIP);
  }

  /**
   * 是否是被屏蔽的ServerIP，如果是，则client无法访问该IP
   *
   * @param serverIP 服务端IP
   * @return 是否是被屏蔽的IP
   */
  public static boolean isShielding(String serverIP) {
    if (StringUtils.isEmpty(serverIP)) {
      throw new IllegalArgumentException("服务器地址为空！无法验证是否是屏蔽IP");
    }
    return serverShieldingIPTable.contains(serverIP);
  }

}
