package com.route.manage.server;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/30 15:02
 */
@SpringBootApplication
public class ManagerServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ManagerServerApplication.class);
  }

  @Bean
  public Gson buildGson() {
    return new Gson();
  }

}
