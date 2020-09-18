package class08;

import java.util.List;

public class Code09_MaxHappy {

    public static class Employee{
        public int happy;
        public List<Employee> nexts;

        public Employee(int happy) {
            this.happy = happy;
        }
    }

    public static int maxHappy(Employee boss){
        if (boss == null){
            return 0;
        }
        return Math.max(process(boss).yes, process(boss).no);
    }

    public static class Info{
        public int yes; // 来的时候最大快乐值
        public int no; // 没来的时候最大快乐值

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info process(Employee employee){
        // 以最基层员工为终止条件(没有下级)
        if (employee.nexts.isEmpty()){
            return new Info(employee.happy, 0);
        }
        // 由于接下来要遍历集合,所以提前将yes和no定义出来
        int yes = employee.happy;
        int no = 0;
        for (Employee next : employee.nexts){
            Info nextInfo = process(next);
            // 来的时候最大值--子节点不来
            yes += nextInfo.no;
            // 不来时候最大值--子节点的最大值
            no += Math.max(nextInfo.no, nextInfo.yes);
        }
        return new Info(yes, no);
    }
}
