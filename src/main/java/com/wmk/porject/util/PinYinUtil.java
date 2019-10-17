package com.wmk.porject.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Sunth
 * @DateTime 2019-04-15 9:49
 * 描述
 */
public class PinYinUtil {
    /**
     * 汉字转大写拼音
     * @param s
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String forQuanPinUp(String s)throws BadHanyuPinyinOutputFormatCombination {
        if(s==null || "".equals(s)){
            return "字符串为空";
        }
        char[] charArray = s.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i],defaultFormat);
                String string =hanyuPinyinStringArray[0];
                pinyin.append(string);
            } else {
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
    /**
     * 汉字转小写拼音
     * @param s
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String forQuanPinLow(String s)throws BadHanyuPinyinOutputFormatCombination {
        if(s==null || "".equals(s)){
            return "字符串为空";
        }
        char[] charArray = s.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i],defaultFormat);
                String string =hanyuPinyinStringArray[0];
                pinyin.append(string);
            } else {
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
    /**
     * 汉字转大写首字母
     * @param s
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String forShouZiMuUp(String s)throws BadHanyuPinyinOutputFormatCombination{
        char[] charArray = s.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        System.err.println(pinyin);
       return pinyin.toString();
    }
    /**
     * 汉字转大写首字母
     * @param s
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String forShouZiMuLow(String s)throws BadHanyuPinyinOutputFormatCombination{
        char[] charArray = s.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        return pinyin.toString();
    }
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    public  static String upperFirstChar(String str) {
        char[] cs=str.toCharArray();
        if ((cs[0] >= 'a') && (cs[0] <= 'z')) {
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }
    public  static String subStringOmit(String subject,  int size) {
        if  (subject != null && subject.length() > size) {
            subject = subject.substring(0,  size) + "...";
        }
        return subject;
    }
    public  static String rtrim(String str,  int num) {
        if  (str.length() > num) {
            str = str.substring(0,  str.length() - num);
        }
        return str;
    }
    public  static String left(String input,  int count) {
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0,  count);
    }
}


