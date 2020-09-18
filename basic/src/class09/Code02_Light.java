package class09;

public class Code02_Light {

    public static int leastLights(String str){
        int light = 0;
        int i = 0;
        char[] chars = str.toCharArray();
        while (i < chars.length){
            if (chars[i] == 'X'){
                i++;
            }else {
                light++;
                if (i + 1 == chars.length){
                    break;
                }else {
                    if (chars[i + 1] == 'X'){
                        i += 2;
                    }else {
                        i += 3;
                    }
                }
            }
        }
        return light;
    }

    public static void main(String[] args) {
        String s = "XX..X.......XX.X";
        System.out.println(leastLights(s));
    }
}