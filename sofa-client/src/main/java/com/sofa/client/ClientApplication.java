package com.sofa.client;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/16 17:19
 */
@SpringBootApplication
@EnableApolloConfig
@EnableSwagger2
public class ClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class);
  }

}
