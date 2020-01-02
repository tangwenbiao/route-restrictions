package com.sofa.client.core.utils;

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

}
