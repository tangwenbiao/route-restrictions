package com.sofa.client.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author: TangFenQi
 * @description: ip相关操作的工具类
 * @date：2019/12/20 17:48
 */
public class IPUtils {

  public static InetAddress getLocalHostLANAddress() throws UnknownHostException {
    try {
      InetAddress candidateAddress = null;
      // 遍历所有的网络接口
      for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
        NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
        // 在所有的接口下再遍历IP
        for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
          InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
          if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
            if (inetAddr.isSiteLocalAddress()) {
              // 如果是site-local地址，就是它了
              return inetAddr;
            } else if (candidateAddress == null) {
              // site-local类型的地址未被发现，先记录候选地址
              candidateAddress = inetAddr;
            }
          }
        }
      }
      if (candidateAddress != null) {
        return candidateAddress;
      }
      // 如果没有发现 non-loopback地址.只能用最次选的方案
      InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
      if (jdkSuppliedAddress == null) {
        throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
      }
      return jdkSuppliedAddress;
    } catch (Exception e) {
      UnknownHostException unknownHostException = new UnknownHostException(
          "Failed to determine LAN address: " + e);
      unknownHostException.initCause(e);
      throw unknownHostException;
    }
  }

  public static void main(String[] args) {
    try {
      System.out.println(IPUtils.getLocalHostLANAddress().getHostAddress());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    System.out.println();
  }

}
