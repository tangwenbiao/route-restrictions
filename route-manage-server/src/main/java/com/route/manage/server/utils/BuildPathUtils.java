package com.route.manage.server.utils;

import com.route.manage.server.constants.RouteManageConstants;

/**
 * @author: TangFenQi
 * @description: 构建sofa在zk的路径
 * @date：2020/1/2 10:38
 */
public class BuildPathUtils {

  public static String buildRoot(String serviceName) {
    return RouteManageConstants.ZK_SOFA_SERVER_PREFIX + serviceName;
  }

  public static String buildService(String serviceName){
    return RouteManageConstants.ZK_SOFA_SERVER_PREFIX+serviceName+RouteManageConstants.ZK_SEPARATOR+RouteManageConstants.ZK_SOFA_SERVER_PROVIDER;
  }

}
