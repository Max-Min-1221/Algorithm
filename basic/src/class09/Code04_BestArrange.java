package class09;

import java.util.Arrays;
import java.util.Comparator;

public class Code04_BestArrange {

    public static class Program{
        public int start; // 开始时间
        public int end;  // 结束时间

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 定义一个比较器,将结束时间更早的放在前面
    public static class MyComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs){
        if (programs == null || programs.length == 0){
            return 0;
        }
        // 经过排序,按照结束时间早晚排好
        Arrays.sort(programs, new MyComparator());
        int curTime = 0;
        int times = 0;
        for (int i = 0; i < programs.length; i++){
            if (curTime <= programs[i].start){
                curTime = programs[i].end;
                times++;
            }
        }
        return times;
    }
}