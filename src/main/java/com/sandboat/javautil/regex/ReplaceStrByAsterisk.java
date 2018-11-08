package com.sandboat.javautil.regex;

/**
 * @author lyk
 * @Title: ReplaceStrByAsterisk
 * @PACKAGE_NAME com.sandboat.javautil.regex
 * @Description: 替换字符串中间为*号
 * @date 2018/11/815:42
 */
public class ReplaceStrByAsterisk {

    public static void main(String[] args) {

        String str = "1111111";


        System.out.println(replace(str));
        System.out.println(replace("2ewer12121"));
        System.out.println(replace("bfvdsew211dfghjk"));
        System.out.println(replace(str));
    }


    public static String replace(String str){
        String result =null;

        if (str.length() <= 1) {
            result = "*";
        } else if (str.length() == 2) {
            result = str.replaceAll( "(?<=\\w{0})\\w(?=\\w{1})","*");
        } else if (str.length() >= 3 && str.length() <= 6) {
            result = str.replaceAll( "(?<=\\w{1})\\w(?=\\w{1})","*");
        } else if (str.length() == 7) {
            result = str.replaceAll( "(?<=\\w{1})\\w(?=\\w{2})","*");
        } else if (str.length() == 8) {
            result = str.replaceAll( "(?<=\\w{2})\\w(?=\\w{2})","*");
        } else if (str.length() == 9) {
            result = str.replaceAll( "(?<=\\w{2})\\w(?=\\w{3})","*");
        } else if (str.length() == 10) {
            result = str.replaceAll( "(?<=\\w{3})\\w(?=\\w{3})","*");
        } else if (str.length() >= 11) {
            result = str.replaceAll( "(?<=\\w{3})\\w(?=\\w{4})","*");
        }
        return result;
    }

}
