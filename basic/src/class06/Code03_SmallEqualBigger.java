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

    private static void swap(Node[] nodeArr, int i, int j) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }
}
