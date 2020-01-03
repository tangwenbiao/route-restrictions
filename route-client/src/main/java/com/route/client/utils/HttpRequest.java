package com.route.client.utils;

import com.route.client.core.ClientConstants;
import lombok.Data;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 16:52
 */
@Data
public class HttpRequest {

  private String url;
  private Integer readTimeOut;
  private Integer connectTimeOut;

  public HttpRequest() {
    readTimeOut = ClientConstants.DEFAULT_READ_TIME;
    connectTimeOut = ClientConstants.DEFAULT_CONNECTOR_TIME;
  }
}
