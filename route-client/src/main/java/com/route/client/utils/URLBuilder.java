package com.route.client.utils;

import com.route.client.core.ClientConfig;
import com.route.client.core.ClientConstants;

/**
 * @author: TangFenQi
 * @description: 构建服务端访问的url
 * @date：2020/1/3 14:55
 */
public class URLBuilder {

  public static String buildQueryAll(ClientConfig clientConfig) {
    String stringBuffer = clientConfig.getAddress()
        + ClientConstants.QUERY_ALL_URL;
    return stringBuffer;
  }

}
