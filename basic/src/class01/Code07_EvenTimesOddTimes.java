package class01;

public class Code07_EvenTimesOddTimes {

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,2,2,3,3,3,2,3,3,3,4,4,3};
        //System.out.println(findOddTimesNum1(arr));
        findOddTimesNum2(arr);
    }

    // 一种数出现奇数次，其余数出现偶数次
    // 找出该奇数
    public static int findOddTimesNum1(int[] arr){
        int eor = 0;
        for (int value : arr) {
            eor ^= value;
        }
        return eor;
    }

    // 两种数出现奇数次，其余数出现偶数次
    // 找出该奇数
    public static void findOddTimesNum2(int[] arr){
        int eor = 0;
        for(int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        // eor = a^b
        // 011101000
        int rightOne = eor & (~eor + 1); // 提取出最右侧的1
        // 000001000
        int eor_ = 0;
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & rightOne) != 0){  // 说明arr[i]在某(4)个位置上有1
                eor_ ^= arr[i];
            }
        }
        System.out.println(eor + "和" + (eor_^eor));
    }

    public static int bitCount(int n){
        int count = 0;
        while (n != 0){
            count++;
            int rightOne = n & (~n + 1);
            n ^= rightOne;
        }
        return count;
    }

}
