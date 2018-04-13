package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4j {
    public static String dateToString() {
        String dateStr = "";
        Date date = new Date();
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateStr = simpleDateFormat.format(date);
        }
        return dateStr;
    }

    /**
     * 瀛楃涓查泦鍚堣浆鎹㈠瓧绗︿覆(閫楀彿鍒嗛殧)
     * 
     * @author wyh
     * @param stringSet
     * @return
     */
    public static String makeStringByStringSet(Set<String> stringSet) {
        String str = dateToString().trim();
        Iterator iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            str = (java.lang.String) iterator.next();
            System.out.println(str);
        }
        return str.toString().toLowerCase();
    }

    /**
     * @param b
     * @return
     * @author 鐢樺缓鏂� 2012-8-16涓嬪崍01:11:49
     */
    private static String[] String(boolean b) {
        return null;
    }

    /**
     * 鑾峰彇鎷奸煶闆嗗悎
     * 
     * @author wyh
     * @param src
     * @return Set<String>
     */
    public static Set<String> getPinyin(String src) {
        if (src != null && !src.trim().equalsIgnoreCase("")) {
            char[] srcChar;
            srcChar = src.toCharArray();
            // 姹夎鎷奸煶鏍煎紡杈撳嚭绫�
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 杈撳嚭璁剧疆锛屽ぇ灏忓啓锛岄煶鏍囨柟寮忕瓑
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[src.length()][];
            for (int i = 0; i < srcChar.length; i++) {
                char c = srcChar[i];
                // 鏄腑鏂囨垨鑰卆-z鎴栬�A-Z杞崲鎷奸煶(鎴戠殑闇�眰锛屾槸淇濈暀涓枃鎴栬�a-z鎴栬�A-Z)
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(
                                srcChar[i], hanYuPinOutputFormat);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                } else if (((int) c >= 65 && (int) c <= 90)
                        || ((int) c >= 97 && (int) c <= 122)) {
                    temp[i] = new String[] { String.valueOf(srcChar[i]) };
                } else {
                    temp[i] = new String[] { String.valueOf(srcChar[i]) };
                }
            }
            String[] pingyinArray = Exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pingyinArray.length; i++) {
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }

    /**
     * 閫掑綊
     * 
     * @author wyh
     * @param strJaggedArray
     * @return
     */
    public static String[] Exchange(String[][] strJaggedArray) {
        String[][] temp = DoExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 閫掑綊
     * 
     * @author wyh
     * @param strJaggedArray
     * @return
     */
    private static String[][] DoExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if (len >= 2) {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int Index = 0;
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    Index++;
                }
            }
            String[][] newArray = new String[len - 1][];
            for (int i = 2; i < len; i++) {
                newArray[i - 1] = strJaggedArray[i];
            }
            newArray[0] = temp;
            return DoExchange(newArray);
        } else {
            return strJaggedArray;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "山西";
        System.out.println(makeStringByStringSet(getPinyin(str)));
    }
}