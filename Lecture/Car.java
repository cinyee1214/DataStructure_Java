package Lecture;

public class Car {
    private String model;
    private int year;
    private double engineSize;

    public Pair<String, Integer> getModelYear() {
        return new Pair<String, Integer>(model, year);
    }

    public Pair<Integer, Double> getYearEngine(){
        return new Pair<Integer, Double>(year, engineSize);
    }

    public Car(String s, int i, double j){
        model = s;
        year = i;
        engineSize = j;
    }



    public static void main(String[] args){

    }
}
