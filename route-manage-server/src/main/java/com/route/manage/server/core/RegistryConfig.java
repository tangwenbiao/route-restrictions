package com.route.manage.server.core;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: TangFenQi
 * @description: 注册信息
 * @date：2019/12/31 14:38
 */
@NoArgsConstructor
@Data
public class RegistryConfig {

  private String address;

  private Map<String,Integer> IPAndPortMap;

}
