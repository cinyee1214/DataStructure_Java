package Lecture;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueStackImpl {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    public boolean offer(int a ){
        stack1.push(a);
        return true;
    }
    public int poll(){
        if(stack1.empty()&&stack2.empty()){
            throw new NoSuchElementException();
        }
        if (stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    public int peek(){
        if(stack1.empty()&&stack2.empty()){
            throw new NoSuchElementException();
        }
        if (stack2.empty()){
            while(!stack1.empty()){
                stack1.push(stack2.pop());
            }
        }
        return stack2.peek();
    }

    public static void main(String[] args) {
        QueueStackImpl a =new QueueStackImpl();
        a.offer(1);
        a.offer(4);
        a.offer(3);
        System.out.println(a.offer(6));
        System.out.println(a.poll());
        System.out.println(a.peek());
        System.out.println(a.poll());
    }

}
