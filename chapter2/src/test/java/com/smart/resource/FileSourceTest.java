package com.smart.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.*;

import java.io.InputStream;

import static org.testng.Assert.*;

public class FileSourceTest {

    @Test
    public  void loadResource() {
        try {
            String filePath = "D:/actionSpring/chapter/chapter2/src/main/resources/conf/file1.txt";
            Resource res1 = new FileSystemResource(filePath);
            Resource res2 = new ClassPathResource("conf/file1.txt");
            assertNotNull(res1);
            assertNotNull(res2);
            assertNotNull(res1.getFilename());
            assertNotNull(res2.getFilename());
            InputStream ins1 = res1.getInputStream();
            InputStream ins2 = res2.getInputStream();
            assertNotNull(ins1);
            assertNotNull(ins2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
