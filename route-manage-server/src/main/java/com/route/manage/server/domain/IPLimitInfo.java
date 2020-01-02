package com.route.manage.server.domain;

import java.util.HashSet;
import lombok.Data;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 15:54
 */
@Data
public class IPLimitInfo {

  /**
   * 需要屏蔽的IP列表
   */
  private HashSet<String> serverShieldingIPTable;

  /**
   * 对上述屏蔽IP无效的Client端IP地址
   */
  private HashSet<String> prerogativeClientIPTable;
}
