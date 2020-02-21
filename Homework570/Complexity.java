package Homework570;

// Xinyi Zhao - CS570 HW2
public class Complexity {

    //method 1: O(n2) -- two n-for loops
    public static void method1(int n){
        int counter = 0;
        for (int i=0; i<n; ++i){
            for (int j = 0; j<n; ++j){
                System.out.println("Operation "+ counter);
                ++ counter;
            }
        }
    }

    //method 2: O(n3) -- three n-for loops
    public static void method2(int n){
        int counter = 0;
        for (int i=0; i<n; ++i){
            for (int j = 0; j<n; ++j){
                for (int k = 0; k<n; ++k) {
                    System.out.println("Operation " + counter);
                    ++counter;
                }
            }
        }
    }

    //method 3: O(logn) -- one logn-for loop
    public static void method3(int n) {
        int counter = 0;
        for (int i = 1; i < n; i *= 2) {
            System.out.println("Operation " + counter);
            ++counter;
        }
    }

    //method 4: O(nlogn) -- one logn-for loop inside of one n-for loop
    public static void method4(int n) {
        int counter = 0;
            for (int i = 0; i < n; ++i) {
                for (int k = 1; k < n; k *= 2) {
                System.out.println("Operation " + counter);
                ++counter;
            }
        }
    }

    //method 5: O(loglogn) -- one loglogn-for loops
    public static void method5(int n) {
        int counter = 0;
        for (int i = 2; i < n; i *= i) {
            System.out.println("Operation " + counter);
            ++counter;
        }
    }

    //method 6: O(2^n) -- recursive
    private static int counter6 = 0;
    public static int method6(int n){
        counter6++;
        System.out.println("Operation " + counter6);
        if (n == 1){
            return 1;
        }else{
            return method6(n-1) + method6(n-1);
        }
    }









    //test:
    public static void main(String [] args){
        System.out.println("test 1:");
        method1(5);  //O(5^2)   -- 25
        System.out.println("test 2:");
        method2(3);  //O(3^3)   --27
        System.out.println("test 3:");
        method3(16);  //O(log16)  --4
        System.out.println("test 4:");
        method4(8);    //O(8*log8)  --24
        System.out.println("test 5:");
        method5(256);    //O(loglog256)  --3
        System.out.println("test 6:");
        method6(5);    //O(2^n) --32
    }
}
