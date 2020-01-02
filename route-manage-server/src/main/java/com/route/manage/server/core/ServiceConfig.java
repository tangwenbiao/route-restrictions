package com.route.manage.server.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: TangFenQi
 * @description: 服务信息
 * @date：2019/12/31 17:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceConfig {

  private String name;

}
