package class06;

public class Code03_SmallEqualBigger {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition1(Node head, int pivot){
        // 先遍历找出链表长度
        if (head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }
        // 将链表放入到数组中
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (; i < nodeArr.length; i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 进行partition
        arrPartition(nodeArr, pivot);
        // 再将数组串起来
        for (i = 1; i < nodeArr.length; i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[nodeArr.length - 1].next = null;
        return nodeArr[0];
    }

    private static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index < big){
            if (nodeArr[index].value < pivot){
                swap(nodeArr, index++, ++small);
            }else if(nodeArr[index].value > pivot){
                swap(nodeArr, index, --big);
            }else {
                index++;
            }
        }
    }

    public static Node listPartition2(Node head, int pivot){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        while (head != null){
            next = head.next; // 用来记录next,然后右移
            head.next = null; // 将head断开
            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾,连等于区域的头，等于区域的尾,连大于区域的头
        if (sT != null){ // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT;  // 让谁去连大于区域的头,谁就变成eT
        }

        // 上面的if不管跑了没有,都是eT去连大于区域的头
        if (eT != null){
            eT.next = mT;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }

    private static void swap(Node[] nodeArr, int i, int j) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }
}
