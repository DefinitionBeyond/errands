package com.campus.dev.util;

class ListNode{
    ListNode next;
    int val;

    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}
class Solution {
    // 请按你的实际需求修改参数
    public ListNode solution(ListNode node) {
        // 在这⾥书写你的代码
        ListNode p = node, q = p.next, r;
        ListNode head;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        node.next = null;
        head = q;
        return head;

    }

}


public class ShowMeBug {
    public static void main(String[] args) {
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null)))));
        Solution instance = new Solution();
        ListNode result = instance.solution(node);

        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

}
//
//// 链表相加
////input:
//// l1: 3 -> 5 -> 7
//// l2: 5 -> 9
//
////output
//// 8 -> 4 -> 8
//
//
//import java.util.Scanner;
//import java.util.Stack;
//import java.util.concurrent.CountDownLatch;
//
//class Node{
//    int val;
//    Node next;
//    public Node(int val){
//        this.val = val;
//    }
//
//}
//
//class Solution {
//    // 请按你的实际需求修改参数
//    public Node solution(Node l1, Node l2) {
//        if(l1 == null)return l2;
//        if(l2 == null)return l1;
//
//        Node answer = new Node(0);
//        Node cursor = answer;
//        int carry = 0;
//        // 在这⾥书写你的代码
//        while(l1 != null || l2 != null || carry != 0) {
//            int l1Val = l1 != null ? l1.val : 0;
//            int l2Val = l2 != null ? l2.val : 0;
//            int sumVal = l1Val + l2Val + carry;
//            carry = sumVal / 10;
//
//            Node sumNode = new Node(sumVal % 10);
//            cursor.next = sumNode;
//            cursor = sumNode;
//
//            if(l1 != null) l1 = l1.next;
//            if(l2 != null) l2 = l2.next;
//        }
//
//        return answer.next;
//    }
//
//    public void add(Node l1, Node l2, int carry){
//        if(l1.next == null && l2.next == null){
//            if(carry != 0){
//                l1.next = new Node(carry);
//            }
//            return;
//        }
//        int l1Val = l1 != null ? l1.val : 0;
//        int l2Val = l2 != null ? l2.val : 0;
//        int sumVal = l1Val + l2Val + carry;
//        carry = sumVal / 10;
//        if(l1 == null){
//
//        }
//        l1.val = sumVal % 10;
//        add(l1.next == null? null:l1, l2.next == null? null:l2 ,carry);
//
//    }
//}
//
//
//
//public class ShowMeBug {
//    static class MyQueue {
//        //push
//        static Stack<Object> s1 = new Stack<>();
//
//        //pop
//        static Stack<Object> s2 = new Stack<>();
//
//        public static boolean push(int val) {
//            s1.push(val);
//
//            return true;
//        }
//
//        public static Object pop() {
//            if(s2.isEmpty()){
//                while (!s1.isEmpty())
//                s2.push(s1.pop());
//            }
//            return s2.pop();
//        }
//
//    }
//
//    public static void main(String[] args) {
//
//        MyQueue.push(3);
//        MyQueue.push(2);
//        MyQueue.push(1);
//        System.out.println(MyQueue.pop());
//        MyQueue.push(4);
//        System.out.println(MyQueue.pop());
//        System.out.println(MyQueue.pop());
//        System.out.println(MyQueue.pop());
//
////        Scanner in = new Scanner(System.in);
////
////        while (in.hasNext()){
////
////            String type = in.next();
////            if(type.equals( "i")){
////                int a = in.nextInt();
////                MyQueue.push(a);
////            }
////
////            else if(type.equals( "o")){
////                System.out.println(MyQueue.pop());
////            }else {
////
////            }
////
////
////        }
//
//
////        Node l1 = new Node(3);
////        Node p = l1;
////        p.next = new Node(5);
////        p = p.next;
////        p.next = new Node(7);
////
////        Node l2 = new Node(5);
////        Node q = l2;
////        q.next = new Node(9);
////
////        Solution instance = new Solution();
////        Node answer = instance.solution(l1,l2);
////        if(answer != null) {
////            System.out.print(answer.val);
////            answer = answer.next;
////        }
////
////        while (answer != null){
////            System.out.print(" -> "+answer.val);
////            answer = answer.next;
////        }
//    }
//}
