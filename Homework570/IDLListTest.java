package Homework570;

public class IDLListTest {
    public static void main(String[] args){
        IDLList<String> idlList1 = new IDLList<>();
//        IDLList<Integer> idlList2 = new IDLList<>();
        idlList1.add("abc");
        idlList1.add("123");
        idlList1.append("0123");
        idlList1.append("a");
        idlList1.append("b");
        idlList1.append("c");
        idlList1.append("d");
//        System.out.println(idlList1.remove("abc"));
//        System.out.println(idlList1.add("abcde"));
//        System.out.println(idlList1.add(3, "abc"));
//        System.out.println(idlList1.add(2,"abc"));
//        System.out.println(idlList1.append("abcd"));
//        System.out.println(idlList1.get(1));
//        System.out.println(idlList1.toString());
//        System.out.println(idlList1.remove("abc"));
        System.out.println(idlList1.toString());
        System.out.println(idlList1.getHead());
        System.out.println(idlList1.getLast());
        System.out.println(idlList1.size());
        System.out.println(idlList1.removeAt(2));
        System.out.println(idlList1.removeLast());
        System.out.println(idlList1.toString());
        System.out.println(idlList1.removeLast());
        System.out.println(idlList1.remove());
        System.out.println(idlList1.toString());
    }
}
