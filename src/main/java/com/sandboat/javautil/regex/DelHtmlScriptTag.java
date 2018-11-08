package com.sandboat.javautil.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyk
 * @Title: DelHtmlScriptTag
 * @PACKAGE_NAME com.sandboat.javautil.regex
 * @Description: 过滤脚本非法字符
 * @date 2018/11/815:53
 */
public class DelHtmlScriptTag {

    public  static String delScriptTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        return htmlStr.trim(); //返回文本字符串
    }

    public static void main(String[] args){

    }
}
