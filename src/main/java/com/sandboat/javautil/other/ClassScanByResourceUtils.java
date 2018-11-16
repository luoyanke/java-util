package com.sandboat.javautil.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyk
 * @Title: ClassScanByResourceUtils
 * @Description: 类扫描，使用spring作为框架可用
 * @date 2018/11/1515:15
 */
public class ClassScanByResourceUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Class> searchClassByIntgerface(Class intgerfaceClass) throws ClassNotFoundException, IOException {
        List<Class> classes = new ArrayList<>();
        for (String s : this.getAllClass()) {
            Class cls = Class.forName(s);
            Class[] allInterfaces = cls.getInterfaces();
            if (allInterfaces == null) continue;
            for (Class clazz : allInterfaces) {
                if (clazz == intgerfaceClass) classes.add(cls);
            }
        }
        return classes;
    }

    public List<Class> searchClassByAnnotaion(Class annotaion) throws ClassNotFoundException, IOException {
        List<Class> classes = new ArrayList<>();
        Class annotaionCls = Class.forName(annotaion.getName());
        for (String s : this.getAllClass()) {
            Class cls = Class.forName(s);
            Annotation annotation = cls.getAnnotation(annotaionCls);
            if (annotation == null) continue;
            classes.add(cls);
        }
        return classes;
    }


    private List<String> getAllClass() throws IOException {
        List<String> classNames = new ArrayList<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources("cn/huacloud/**");
            for (Resource resource : resources) {
                if (!resource.getFilename().endsWith(".class")) continue;

                String filePath = resource.getURL().getPath();
                if (filePath.lastIndexOf("classes!/") < 0) {
                    filePath = filePath.substring(filePath.lastIndexOf("classes/") + 8, filePath.length());
                } else {
                    filePath = filePath.substring(filePath.lastIndexOf("classes!/") + 9, filePath.length());
                }
                filePath = filePath.replace("/", ".");
                filePath = filePath.substring(0, filePath.lastIndexOf("."));
                classNames.add(filePath);

            }
        } catch (IOException e) {
            logger.error("遍历class路径出错", e);
            throw e;
        }
        return classNames;
    }


}
