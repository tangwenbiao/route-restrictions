package com.ruote.manage.server.test.utils;

import com.route.manage.server.utils.AddressUtils;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Assert;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/12/31 15:43
 */
public class AddressUtilsTest {

  public static void main(String[] args) {
    //单个
    String exampleOne = "10.27.55.15:1111";
    Map<String, Integer> stringIntegerMap = AddressUtils.parseAddress(exampleOne);
    Assert.assertEquals(stringIntegerMap.size(), 1);
    for (Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
      Assert.assertEquals(entry.getKey(),"10.27.55.15");
      Assert.assertEquals(1111, (int) entry.getValue());
    }
    //多个
    String exampleTwo="10.27.55.15:1111,10.27.55.16:2222";
    stringIntegerMap=AddressUtils.parseAddress(exampleTwo);
    Assert.assertEquals(stringIntegerMap.size(),2);
    Assert.assertTrue(stringIntegerMap.containsKey("10.27.55.15"));
    Assert.assertTrue(stringIntegerMap.containsKey("10.27.55.16"));
    Assert.assertEquals((int)stringIntegerMap.get("10.27.55.15"),1111);
    Assert.assertEquals((int)stringIntegerMap.get("10.27.55.16"),2222);
  }

}
