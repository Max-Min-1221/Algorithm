package class12;

import java.util.Scanner;

public class Exam {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                char c = s.charAt(i);
                s = s.replace(c, ' ');
            }
        }
        String[] str = s.split(" ");
        for (int i = 0; i < str.length; i++){
            if (str[i] != null && str[i].length() != 0){
                if (Integer.parseInt(str[i]) >= 1000 && Integer.parseInt(str[i]) <= 3999){
                    System.out.print(Integer.parseInt(str[i]) + " ");
                }
            }
        }
    }
}
