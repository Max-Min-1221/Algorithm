package class09;

import java.util.Arrays;
import java.util.Comparator;

public class Code01_LowestLexicography {

    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            // String中的方法
            // 返回负数,说明 o1 + o2 < o2 + o1
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (String str: strs){
            res += str;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = {"bc", "ad", "ab", "de"};
        String string = lowestString(strings);
        System.out.println(string);
    }
}
