package practice;

public class Lecture {
    private int[] ar = new int[5];
    private int idx = 0;
    public void addValue(int x) throws ArrayIndexOutOfBoundsException {
        ar[idx]=x;
        idx++;
    }
    public void printAr(){
        System.out.println("Array [] = ");
        for (int i=0; i<idx;++i){
            System.out.println(ar[i]);
        }
    }
    public static void main(String[] args){
        Lecture l1 = new Lecture();

        for (int i=0;i<6;++i){
            try{
                l1.addValue(i);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
        l1.printAr();
    }

}
