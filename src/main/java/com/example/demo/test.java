package com.example.demo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Path;

public class test {
    public static void main(String[] args) throws IOException {
//        String txt = "";
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver ();
//        Resource[] resources = resolver.getResources("templates/layout/email.html");
//        Resource resource = resources[0];
//
////获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
//        InputStream stream = resource.getInputStream();
//        StringBuilder buffer = new StringBuilder();
//        byte[] bytes = new byte[1024];
//        try {
//            for (int n; (n = stream.read(bytes)) != -1; ) {
//                buffer.append(new String(bytes, 0, n));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        txt = buffer.toString();
//        String jar_parent = new File (ResourceUtils.getURL("classpath:").getPath()).getParentFile().getParentFile().getParent();
//
//
//        System.out.println (jar_parent);
        File file = new File ("file:static/");
        System.out.println (file.exists ());
        file = new File ("static/");
        System.out.println (file.exists ());
        File root_path = ResourceUtils.getFile ("file:static/img/userPicture");
        System.out.println (root_path.getAbsolutePath ());
        if(!root_path.exists ())root_path.mkdirs ();;
    }
}
