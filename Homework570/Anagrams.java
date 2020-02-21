package Homework570;

import Lecture.ArrayListImpHeap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** @author Xinyi Zhao
 */

public class Anagrams {
    //fields
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    //constructor
    public Anagrams() {
        letterTable = new HashMap<>();
        anagramTable = new HashMap<>();
        buildLetterTable();
    }

    //method
    private void buildLetterTable() {
        for (int i = 0; i < 26; ++i) {
            letterTable.put((char) (i + 97), primes[i]);
        }
    }

    private void addWord(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException();
        }

        Long key = myHashCode(s);
        if (!anagramTable.containsKey(key)) {
            anagramTable.put(key, new ArrayList<String>());
        }
        anagramTable.get(key).add(s);
    }

    private Long myHashCode(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException();
        }

        Long hashCode = Long.valueOf(1);
        for (int i = 0; i < s.length(); ++i) {
            hashCode *= letterTable.get(s.charAt(i));
        }
        return hashCode;
    }

    public void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> result = new ArrayList<>();
        int largestNum = 0;

        for (Map.Entry<Long, ArrayList<String>> entry: anagramTable.entrySet()) {
            if (largestNum < entry.getValue().size()) {
                largestNum = entry.getValue().size();
                ArrayList<Map.Entry<Long, ArrayList<String>>> tmp = new ArrayList<>();
                tmp.add(entry);
                result = tmp;
            } else if (largestNum == entry.getValue().size()) {
                result.add(entry);
            }
        }

        return result;
    }

    //main method
    public static void main(String[] args) {
        Anagrams a = new Anagrams ();

        final long startTime = System.nanoTime();
        try {
            a.processFile("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time: "+ seconds);
        for (int i = 0; i < maxEntries.size(); ++i) {
            System.out.println("Key of max anagrams: " + maxEntries.get(i).getKey());
            System.out.println("List of max anagrams: " + maxEntries.get(i).getValue());
            System.out.println("Length of list of max anagrams: " + maxEntries.get(i).getValue().size());
        }
    }
}
