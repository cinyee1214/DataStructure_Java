package Lecture;

public class DirectoryEntry {
    private String name;
    private String number;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public DirectoryEntry(String name, String number){
        this.name = name;
        this.number = number;
    }



    @Override
    public boolean equals(Object obj) {
        DirectoryEntry tmp = (DirectoryEntry) obj;
        if (this.getName().equals(tmp.getName())){
            return true;
        }else{
            return false;
        }
    }



}
