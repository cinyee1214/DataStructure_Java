package Homework570;

// name: Xinyi Zhao
// I use big-endian in this code.


import java.util.Arrays;

public class BinaryNumber {
    /** 2.1 Basic operations
     * A constructor BinaryNumber(int length) for creating a binary number of length length and consisting only of zeros.
     */
    private int[] data;
    public BinaryNumber (int length){
        data = new int[length];
        for(int i = 0; i < length; i++){
            data[i] = 0;
        }
    }

    /** A constructor BinaryNumber(String str) for creating a binary number given a string.
     */
    public BinaryNumber(String str){
        data = new int[str.length()];
        for (int j = 0; j < str.length(); j++){
            char ch = str.charAt(j);
            data[j] = Character.getNumericValue(ch)  ;           // data[j] = (int) ch - '0';
        }
    }

    /* An operation int getLength() for determining the length of a binary number.
     */
    public int getLength() {
        int length = data.length;
        return length;
    }

    /* An operation int getDigit(int index) for obtaining a digit of a binary number given an index.
    The starting index is 0.
    If the index is out of bounds, then a message should be printed on the screen indicating this fact.
     */
    public int getDigit(int index) {
        int length = getLength();
        int Digit = -1;
        if (index >= 0 && index < length) {
            Digit = data[index];
            System.out.println("The digit at the index " + index + " is " + Digit);
        } else {
            System.out.println("Note: The index is out of bounds.");
        }
        return Digit;
    }

    /* An operation int toDecimal() for transforming a binary number to its decimal notation
     */
    public int toDecimal() {
        int length = getLength();
        int decimal = 0;
        for (int i = 0; i < length; i++){
            decimal = decimal + data[i]*(int)Math.pow(2,length-1-i);
        }
        return decimal;
    }

    /* An operation void shiftR(int amount) for shifting all digits in a binary number any number of places to the right,
     as indicated by a parameter amountToShift.
     */
//method 1:
//    public void shiftR(int amount){
//        int [] shiftdata = new int[getLength()+amount];
//        for (int i = 0; i < amount; i++){
//            shiftdata[i] = 0;
//        }
//        for (int j=amount; j < amount+getLength(); j++){
//            shiftdata[j] = data[j-amount];
//        }
//        for (int m =0; m < amount+getLength();m++){
//            System.out.print(shiftdata[m]);
//        }
//        System.out.println();
//    }

    //method 2 by hints:
    public void shiftR(int amount) {
        int [] newData = reallocate(amount);
        for (int i = 0; i < amount; i++) {
            newData[i] = 0;
        }
        for (int j = amount; j < newData.length; j++) {
            newData[j] = data[j - amount];
        }
        for (int m = 0; m < newData.length; m++) {
            System.out.print(newData[m]);
        }
        System.out.println();
    }
    private int[] reallocate(int amount){
        int[] newData = Arrays.copyOf(data, amount + getLength());
        return newData;
    }


    /* 2.2 Addition of Binary Numbers
     */
    /* void add(BinaryNumber aBinaryNumber) for adding two binary numbers
     */
    private boolean overflow;

    public void add(BinaryNumber aBinaryNumber) {
        int alength = data.length;
        int blength = aBinaryNumber.getLength();
        if(alength != blength) {
            System.out.println("The lengths of the binary numbers do not coincide.");
        } else {
            int[] result = new int[alength];
            int x = 0;
            for(int i = alength - 1; i >= 0; i--) {
                  result[i]= x + data[i] + aBinaryNumber.data[i];
                  if (result[i] == 2){
                      x = 1;
                      result[i] = 0;
                  } else if (result[i] == 3) {
                      x = 1;
                      result[i] = 1;
                  } else {
                      x = 0;
                  }
            }
            overflow = (x == 0);
            data = Arrays.copyOf(result, alength);
            for(int j = 0; j < alength; j++) {
                System.out.print(data[j]);
            }
            System.out.println();
            System.out.println("The flag is " + overflow);
        }
    }

    /* An operation clearOverflow() that clears the overflow flag.
     */
    public void clearOverflow(){
         overflow = false;
         System.out.println("Overflow has been cleared");
    }

    /* An operation String toString() for transforming a binary number to a String.
     * If the number is the result of an overflow, the string “Overflow” should be returned.
     */
    public String toString(){
        StringBuilder dataString = new StringBuilder();
        if (overflow) {
            for(int i=0; i< data.length; i++) {
                dataString.append(data[i]);
            }
        } else {
            dataString.append("Overflow");
        }
        return dataString.toString();
    }









    /* Test
     */
    public static void main(String[] args){
        BinaryNumber bn = new BinaryNumber(4);
        for (int i = 0; i < bn.data.length; i++) {
            System.out.print(bn.data[i]);
        }
        System.out.println();
        BinaryNumber n = new BinaryNumber("1101");
        for (int i = 0; i < n.data.length; i++) {
            System.out.print(n.data[i]);
        }
        System.out.println();
        System.out.println( bn.getLength ());
        System.out.println(n.getDigit(2));
        System.out.println(n.getDigit(5));
        System.out.println(n.toDecimal());
        n.shiftR(4);

        BinaryNumber a = new BinaryNumber("0001");
        n.add(a);
//        n.clearOverflow();
        System.out.println(n.toString());

    }












}
