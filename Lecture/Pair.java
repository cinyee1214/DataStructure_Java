package Lecture;

public class Pair<E, F> {
    private E s;
    private F i;

    Pair(E s, F i){
        this.s = s;
        this.i = i;
    }
    public E getS(){
        return s;
    }
    public void setS(E s){
        this.s = s;
    }
    public F getI(){
        return i;
    }
    public void setI(F i){
        this.i = i;
    }


}
