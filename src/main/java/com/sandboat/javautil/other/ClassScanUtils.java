package com.sandboat.javautil.other;


import com.google.common.collect.Lists;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyk
 * @Title: ClassScanUtils
 * @Description: 类扫描，如果使用jar方式启动这个类会有问题 ，
 * @see ClassScanByResourceUtils 可以已流的方式获取
 * @date 2018/10/179:53
 */
public class ClassScanUtils {

    List<String> classPaths = new ArrayList<String>();

    public List<Class> searchClassByIntgerface(Class intgerfaceClass) throws ClassNotFoundException {

        List<Class> list = Lists.newArrayList();

        String classpath = ClassScanUtils.class.getResource("/").getPath();
        doPath(new File(classpath));
        for (String s : classPaths) {
            s = s.replace(classpath.replace("/", "\\")
                    .replaceFirst("\\\\", ""), "")
                    .replace("\\", ".")
                    .replace(".class", "");
            Class cls = Class.forName(s);
            Class[] allInterfaces = cls.getInterfaces();
            if(allInterfaces==null)continue;
            for (Class clazz : allInterfaces) {
                if( clazz==intgerfaceClass) list.add(cls);
            }
        }
        return list;
    }

    public Class searchClassByAnnotaion(Class annotaion) throws ClassNotFoundException {

        List<Class> list = Lists.newArrayList();

        String classpath = ClassScanUtils.class.getResource("/").getPath();
        doPath(new File(classpath));
        for (String s : classPaths) {
            s = s.replace(classpath.replace("/", "\\")
                    .replaceFirst("\\\\", ""), "")
                    .replace("\\", ".")
                    .replace(".class", "");
            Class cls = Class.forName(s);
            Annotation annotation = cls.getAnnotation(annotaion);
            if(annotation!=null)return cls;
        }
        return null;
    }

    /**
     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
     *
     * @param file
     */
    private void doPath(File file) {
        if (file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {//标准文件
            //标准文件我们就判断是否是class文件
            if (file.getName().endsWith(".class")) {
                //如果是class文件我们就放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
    }

}