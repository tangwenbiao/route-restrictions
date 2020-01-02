package com.route.manage.server.controller;

import com.route.manage.server.core.LimitConfigServer;
import com.route.manage.server.domain.IPLimitInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 16:04
 */
@RestController
@RequestMapping("/limit")
public class LimitController {

  @Autowired
  private LimitConfigServer limitConfigServer;


  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public List<IPLimitInfo> getAll() {
    return limitConfigServer.getAll();
  }


}

