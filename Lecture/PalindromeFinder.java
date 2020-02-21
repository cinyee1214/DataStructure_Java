package Lecture;
import java.util.*;
/**
 * @author cinyee
 *
 */

public class PalindromeFinder {
    private String inputStr;
    private Stack<Character> myStack = new Stack<>();
    public PalindromeFinder(String str){
        inputStr = str;
        fillStack();
    }
    private void fillStack(){
        for (int i =0; i<inputStr.length();++i){
            myStack.push(inputStr.charAt(i));
        }
    }

    private String buildReverse(){
        StringBuilder result = new StringBuilder();
        while(!myStack.empty()){
            result.append(myStack.pop());
        }
        return result.toString();
    }
    public boolean isPalindrome(){
        return inputStr.equalsIgnoreCase(buildReverse());
    }



    public static void main(String[] args){
        PalindromeFinder p1 = new PalindromeFinder("Anna");
        if (p1.isPalindrome()){
            System.out.println("The string: <" + p1.inputStr + "> is a palindrome!");
        }else{
            System.out.println("The string: <" + p1.inputStr + "> is not a palindrome!");
        }

    }



}
