package com.route.manage.server.utils;

import com.route.manage.server.constants.RouteManageConstants;
import com.route.manage.server.core.ProviderConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author: TangFenQi
 * @description: 对于地址的字符串解析
 * @date：2019/12/31 14:56
 */
@Slf4j
public class AddressUtils {

  /**
   * 解析配置文件中的 地址信息
   *
   * @return key:ip  value:port
   */
  public static Map<String, Integer> parseAddress(String address) {
    if (StringUtils.isEmpty(address)) {
      log.warn("address is empty!");
      return new HashMap<>();
    }
    String[] splitAddress = address.split(",");
    Map<String, Integer> ipAndPortMap = new HashMap<>();
    for (String ad : splitAddress) {
      String ip = ad.substring(0, ad.indexOf(":"));
      int port = Integer.parseInt(ad.substring(ad.indexOf(":") + 1));
      ipAndPortMap.put(ip, port);
    }
    return ipAndPortMap;
  }

  /**
   * 解析zk中写入的服务名称
   *
   * @param path zk中存储的路径
   * @return 服务名称
   */
  public static String parseService(String path) {
    if (path.isEmpty()) {
      log.warn("path is empty!");
      return "";
    }
    int prefix = (RouteManageConstants.ZK_SEPARATOR + RouteManageConstants.ZK_SOFA_ROOT
        + RouteManageConstants.ZK_SEPARATOR).length();
    String substring = path.substring(prefix);
    if (substring.contains("/")) {
      substring = substring.substring(0, substring.indexOf("/"));
    }
    return substring;
  }

  private static String URL_SEPARATOR = "://";

  /**
   * 解析路径中服务信息
   *
   * @param path zk中存储路径
   * @param serviceName 服务名称
   * @return 服务加入者类型
   */
  public static ProviderConfig parseProvider(String path, String serviceName) {
    if (StringUtils.isEmpty(path) || StringUtils.isEmpty(serviceName)) {
      log.warn("path or service name is empty!");
    }
    String prefix =
        RouteManageConstants.ZK_SOFA_SERVER_PREFIX + serviceName + RouteManageConstants.ZK_SEPARATOR
            + RouteManageConstants.ZK_SOFA_SERVER_PROVIDER + RouteManageConstants.ZK_SEPARATOR;
    String substring = path.substring(prefix.length());
    //解码
    String url;
    try {
      url = URLDecoder.decode(substring, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      log.error("parse url of provider is err! ex:", e);
      throw new IllegalArgumentException("解析服务提供者url失败!");
    }
    //解析
    url = url.substring(url.indexOf(URL_SEPARATOR) + URL_SEPARATOR.length());
    String ip = url.substring(0, url.indexOf(":"));
    int port = Integer.parseInt(url.substring(url.indexOf(":") + 1, url.indexOf("?")));
    ProviderConfig providerConfig = new ProviderConfig();
    providerConfig.setIp(ip);
    providerConfig.setPort(port);
    return providerConfig;
  }

}
