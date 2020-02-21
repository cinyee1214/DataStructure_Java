package Lecture;

import java.util.ArrayList;

public class Lecture4 {
//    static ArrayList<Integer> myList;
    public static void main(String[] args){
        ArrayList<Integer> myList = new ArrayList<Integer>();
        myList.add(4);
        myList.add(14);
        myList.add(50);
        myList.set(2,10);
        myList.add(10);
        myList.remove(3);
        myList.indexOf(14);

        System.out.println(myList.indexOf(1));

        System.out.println(myList.get(0));
        System.out.println(myList);


    }




}
