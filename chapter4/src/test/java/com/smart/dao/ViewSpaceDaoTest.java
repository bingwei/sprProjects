package com.smart.dao;

import com.smart.domain.ViewSpace;
import org.testng.annotations.*;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.*;

import static org.testng.Assert.*;

public class ViewSpaceDaoTest extends BaseDaoTest {

    @SpringBeanByName
    private ViewSpaceDao viewSpaceDao;

    @BeforeMethod
    public void init(){
        assertNotNull(viewSpaceDao);
    }

   @Test
    public void addViewSpace(){
       ViewSpace space = new ViewSpace();
       space.setSpaceName("鼓浪屿");
       space.setAddress("厦门");
       space.setDescription("厦门鼓浪屿");
       viewSpaceDao.addViewSpace(space);
       assertTrue(space.getSpaceId()>0);
   }


    @Test
    public void addViewSpaceByNamedParams(){
        ViewSpace space = new ViewSpace();
        space.setSpaceName("鼓浪屿");
        space.setAddress("厦门");
        space.setDescription("厦门鼓浪屿");
        viewSpaceDao.addViewSpaceByNamedParams(space);
    }


    @Test
    public void addViewSpaces(){
        ViewSpace space1 = new ViewSpace();
        space1.setSpaceName("鼓浪屿");
        space1.setAddress("厦门");
        space1.setDescription("厦门鼓浪屿");
        ViewSpace space2 = new ViewSpace();
        space2.setSpaceName("植物园");
        space2.setAddress("厦门");
        space2.setDescription("厦门植物园");
        List spaces = new ArrayList<ViewSpace>();
        spaces.add(space1);
        spaces.add(space2);
        viewSpaceDao.addViewSpaces(spaces);
    }

    @Test
    public void getViewSpaces(){
        List<ViewSpace>  viewSpaces =  viewSpaceDao.getViewSpaces(1,10000);
        assertNotNull(viewSpaces);
        for(ViewSpace viewSpace:viewSpaces){
            assertNotNull(viewSpace);
        }
    }
}
