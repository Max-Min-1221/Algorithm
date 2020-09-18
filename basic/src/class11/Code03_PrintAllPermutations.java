package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code03_PrintAllPermutations {

    public static List<String> permutation(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process(chars, 0, list);
        return list;
    }

    // str[0...i-1] 已经做好决定,不会再改了
    // str[i...] 都有机会来到i位置
    // i终止位置,chars当前的样子,就是一种结果,添加到list中
    public static void process(char[] chars, int i, List<String> list){
        if (i == chars.length){
            // 因为是全排列,所以把最终结果(整个字符串排列顺序不同的结果)放入list
            list.add(String.valueOf(chars));
        }
        // 如果i没有终止,i... (i后面的)都有机会来到i位置
        for (int j = i; j < chars.length; j++){
            swap(chars, i, j);
            process(chars, i + 1, list);
            swap(chars, i, j); // 因为在同一个数组中,所以要恢复现场
        }
    }

    public static void swap(char[] chars, int i, int j){
        char tem = chars[i];
        chars[i] = chars[j];
        chars[j] = tem;
    }

    public static List<String> permutationNoRepeat(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process2(chars, 0, list);
        return list;
    }

    public static void process1(char[] chars, int i, HashSet<String> set){
        if (i == chars.length){
            set.add(String.valueOf(chars));
        }
        for (int j = i; j < chars.length; j++){
            swap(chars, i, j);
            process1(chars, i + 1, set);
            swap(chars, i, j);
        }
    }

    public static void process2(char[] chars, int i, List<String> list){
        if (i == chars.length){
            list.add(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < chars.length; j++){
            if (!visit[chars[j] - 'a']) {
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process2(chars, i + 1, list);
                swap(chars, i, j);
            }
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> list = new ArrayList<>();
        list = permutation(s);
        for (String str: list){
            System.out.println(str);
        }
    }
}