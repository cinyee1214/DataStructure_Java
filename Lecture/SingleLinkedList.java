package Lecture;

//generic

public class SingleLinkedList<E> {
    private Node<E> head =null;
    private int size;

    private static class Node<E>{
        private E data;
        private Node<E> next;

        private Node(E data){
            this.data = data;
            next = null;
        }
        private Node(E data, Node<E> nextRef){
            this.data = data;
            next = nextRef;
        }
        public void setNode(Node<E> nextRef){
            next = nextRef;
        }


    }
    @Override
    public String toString(){
        String value = "My list is: ";
        Node<E> nodeRef = head;
        while (nodeRef!=null){
            value = value + nodeRef.data;
            if (nodeRef.next!=null) {
                value = value + ", ";
            }
            nodeRef = nodeRef.next;
        }
        return value;
    }


    public static void main(String [] args){
        SingleLinkedList<String> myList = new SingleLinkedList<>();
        Node<String> tom = new Node<>("Tom");
        Node<String> john = new Node<>("John");
        Node<String> matt = new Node<>("Matt");
        tom.next = john;
        john.next = matt;
        myList.head = tom;

        System.out.println(myList);


    }


}
