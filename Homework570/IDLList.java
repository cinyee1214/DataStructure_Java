package Homework570;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IDLList<E> {

    private class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;
        public Node(E elem){
            next = prev =null;
            data = elem;
        }

        public Node(E elem, Node<E> prev, Node<E> next){
            data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList() {
        head = tail = null;
        indices = new ArrayList<>();
        size = 0;
    }

    public boolean add (int index, E elem){
        if (index == 0) {
            return add(elem);
        }
        if (index < 0 || index > size) {
            return false;
        }if (index == size) {
            return append(elem);
        } else {
            Node<E> newnode = new Node<>(elem);

            indices.get(index).prev.next = newnode;
            newnode.prev = indices.get(index).prev;
            newnode.next = indices.get(index);
            indices.get(index).prev = newnode;

            indices.add(index, newnode);
            size++;
            return true;
        }
    }

    public boolean add(E elem) {
        if (size == 0) {
            head = tail = new Node<E>(elem);
            size++;
            indices.add(head);
            return true;
        }

        Node<E> newnode = new Node<>(elem);

        newnode.next = head;
        head.prev = newnode;
        head = newnode;

        indices.add(0,head);
        size++;
        return true;
    }

    public boolean append(E elem) {
        if (size == 0) {
            return add(elem);
        } else {
            Node<E> newnode = new Node<>(elem);

            tail.next = newnode;
            newnode.prev = tail;

            tail = newnode;
            indices.add(tail);
            size++;
            return true;
        }
    }

    public E get (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return getHead();
        }
        if (index == size - 1) {
            return getLast();
        }
        return indices.get(index).data;
    }

    public E getHead() {
        if (size == 0) {
            return null;
        }
        return indices.get(0).data;
    }

    public E getLast() {
        if (size == 0) {
            return null;
        }
        return indices.get(size - 1).data;
    }

    public int size(){
        return indices.size();
    }

    public E remove() {
        if (head == null) {
            return null;
        }

        Node<E> tmp = head;

        if (head == tail) {
            head = tail = null;
            indices.remove(0);
            size--;
            return tmp.data;
        }

        head = head.next;
        head.prev = null;
        indices.remove(0);
        size--;
        return tmp.data;
    }

    public E removeLast() {
        if (head == null) {
            return null;
        }

        Node<E> tmp = tail;

        if (head == tail) {
            head = tail = null;
            indices.remove(0);
            size--;
            return tmp.data;
        }

        tail = tail.prev;
        tail.next = null;
        indices.remove(size - 1);
        size--;
        return tmp.data;
    }

    public E removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == size-1) {
            return removeLast();
        }
        if(index == 0) {
            return remove();
        }

        Node<E> tmp = indices.get(index);

        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;

        indices.remove(index);
        size--;
        return tmp.data;
    }

    public boolean remove(E elem) {
        for (int i = 0; i < size; ++i) {
            Node<E> cur = indices.get(i);
            if (cur.data.equals(elem)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        Node<E> cur = head;
        StringBuilder result = new StringBuilder();
        while (cur != null) {
            result.append(cur.data);
            if (cur.next != null) {
                result.append("->");
            }
            cur = cur.next;
        }
        return result.toString();
    }
}
