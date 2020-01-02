package com.route.manage.server.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: TangFenQi
 * @description: 服务提供者信息
 * @date：2019/12/31 16:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderConfig {

  private String ip;

  private int port;

}
