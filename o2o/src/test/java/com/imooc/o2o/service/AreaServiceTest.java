package com.imooc.o2o.service;



import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import static org.junit.Assert.assertEquals;


public class AreaServiceTest extends BaseTest {

	@Autowired
    private AreaService areaService;

	@Test
    public void testGetAreaList(){


      List<Area> areaList=areaService.getAreaList();
      assertEquals("西苑",areaList.get(1).getAreaName());
//      assertEquals(2, areaList.size());
  }


}
