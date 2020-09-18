package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code02_PrintAllSubsequences {

    public static List<String> subsequences(String str){
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        String path = "";
        process(chars, 0, list, path);
        return list;
    }

    // index: 此时来到的位置, 要 or 不要
    // 如果index来到了str中的终止位置,把沿途路径形成的答案,放入ans中
    // 之前做出的选择,就是path
    public static void process(char[] str, int index, List<String> ans, String path){
        if (str.length == index){
            ans.add(path);
            return;
        }
        // 不需要index位置上的字符
        String no = path;
        process(str, index + 1, ans, no);

        // 加上index位置上的字符
        String yes = path + String.valueOf(str[index]);
        process(str, index + 1, ans, yes);
    }

    public static HashSet<String> subsequencesNoRepeat(String str){
        char[] chars = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        String path = "";
        process2(chars, 0, set, path);
        return set;
    }

    public static void process2(char[] str, int index,
                                HashSet<String> ans, String path){
        if (index == str.length){
            ans.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, ans, yes);
    }

    public static void main(String[] args) {
        String s = "aacc";
        HashSet<String> set = new HashSet<>();
        set = subsequencesNoRepeat(s);
        for (String str : set){
            System.out.println(str);
        }
    }
}
