package Lecture;

import java.util.ArrayList;

public class PhoneDirectory {
    static ArrayList<DirectoryEntry> myList = null;
    public static void main(String[] args){
        myList = new ArrayList<DirectoryEntry>();
        DirectoryEntry entry1 = new DirectoryEntry("Tom Jones", "20121234");

        myList.add(entry1);
        myList.add( new DirectoryEntry("Matt Jones", "20121235"));
        System.out.println(myList.get(0).getName());
        myList.add( new DirectoryEntry("Yot Jones", "20121236"));
        myList.add( new DirectoryEntry("Mark Jones", "20121237"));

        int idx = myList.indexOf(new DirectoryEntry("Tom Jones",""));       // if number is an int, how to describe it?

        if(idx!=-1){
            System.out.println(myList.get(idx).getNumber());
        }else{
            System.out.println("I don't have this number!");
        }


    }


}
