package class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {

    public static class Program{
        public int profit;
        public int capital;

        public Program(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    // 按照花费大小排序,花的越少排在越前--小根堆
    public static class CostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.capital - o2.capital;
        }
    }

    // 按照利润大小排序,利润越多排在越前--大根堆
    public static class ProfitComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
        PriorityQueue<Program> costQueue =
                new PriorityQueue<>(new CostComparator());
        PriorityQueue<Program> profitQueue =
                new PriorityQueue<>(new ProfitComparator());
        // 将所有项目放入capital小根堆
        for (int i = 0; i < Profits.length; i++){
            costQueue.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < k; i++){
            // 将能进行的项目都放入profit大根堆中
            while (!costQueue.isEmpty() && costQueue.peek().capital < W){
                profitQueue.add(costQueue.poll());
            }
            // 如果发生一些情况,使得大根堆为空,此时直接返回W
            // 比如说：没有项目可做(资金不够做剩下的项目,项目做完了等)
            if (profitQueue.isEmpty()){
                return W;
            }
            W += profitQueue.poll().profit;
        }
        return W;
    }
}
