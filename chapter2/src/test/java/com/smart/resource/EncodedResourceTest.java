package com.smart.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class EncodedResourceTest {

    @Test
    public  void encodedResource() throws Throwable {
        Resource res = new ClassPathResource("conf/file1.txt");
        EncodedResource encRes = new EncodedResource(res, "UTF-8");
        String content = FileCopyUtils.copyToString(encRes.getReader());
        assertNotNull(content);
        assertTrue(content.contains("景区网站"));
    }
}
