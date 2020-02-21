package Lecture;

public class Recursion {
    private int counter = 0;

    private int length(String s){
        System.out.println("Start of method length() for: <"+s+">");
        if (s == null || s.equals("")){
            System.out.println("End of method length() for: <"+s+">");
            return 0;
        }
        int tmp = length(s.substring(1));
        System.out.println("End of method length() for: <"+s+">");
        return 1+tmp;
    }

    private int factorials(int n){
        if (n<0){
            throw new IllegalArgumentException();
        }
        if (n==0){
            return 1;
        }else{
            return n*factorials(n-1);
        }
    }

    private int fibonacci(int n){
        if(n < 1){
            throw new IllegalArgumentException();
        }
//        ++counter;
        if(n == 2 || n == 1){
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private int fibonacci2(int fibCurrent, int fibPrevious, int n){
        if(n < 1){
            throw new IllegalArgumentException();
        }
        ++counter;
        if ( n == 1){
            return fibCurrent;
        }
        return fibonacci2(fibCurrent + fibPrevious, fibCurrent, n-1);
    }




    public static void main(String[] args){
        Recursion r = new Recursion();
//        System.out.println(r.length("ace"));
//        System.out.println(r.factorials(4));
//        System.out.println(r.fibonacci(12));
        System.out.println(r.fibonacci2(1,0,12));
        System.out.println(r.counter);

    }



}
