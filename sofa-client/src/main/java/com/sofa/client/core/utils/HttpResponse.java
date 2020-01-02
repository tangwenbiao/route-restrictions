package com.sofa.client.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 17:22
 */
@Data
@AllArgsConstructor
public class HttpResponse<T> {

  private T body;
  private int code;


}
