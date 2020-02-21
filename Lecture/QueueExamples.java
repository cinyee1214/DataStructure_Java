package Lecture;

import java.util.*;

public class QueueExamples {
    private static LinkedList<Integer> ll = new LinkedList<>();
    private static Queue<Integer> q = new LinkedList<>();
    private static Stack<Integer> s = new Stack<>();
//    private static Queue<Integer> qq = new Queue<Integer>();            //Queue is an interface and cannot be instantiated
    // superclass ==== new subclass
    public static void main (String[] args){
        ll.add(4,4);
        ll.offer(3);
        ll.remove();
        ll.peek();

        q.offer(4);
        q.remove();
        q.peek();
        q.add(5);
        q.add(4);

        s.push(3);

    }
}
