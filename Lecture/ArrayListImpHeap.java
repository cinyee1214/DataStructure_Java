package Lecture;

import java.util.ArrayList;

public class ArrayListImpHeap<E extends Comparable<E>> {
    ArrayList<E> array = new ArrayList<>();
    public boolean add(E item) {
        if (array == null || array.size() == 0) {
            array.add(item);
            return true;
        }
        if (array.contains(item)) {
            return false;
        }
        array.add(item);
        int i = array.size() - 1;
        while ((i - 1) / 2 >= 0 && array.get(i).compareTo(array.get((i - 1) / 2)) < 0) {
            swap(array, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
        return true;
    }

    public E remove() {
        if (array == null || array.size() == 0) {
            throw new IllegalArgumentException();
        }

        E item = array.get(0);

        int parent = 0;
        while (2 * parent + 2 < array.size()) {
            if (array.get(2 * parent + 1).compareTo(array.get(2 * parent + 2)) < 0) {
                swap(array, parent, 2 * parent + 1);
                parent = 2 * parent + 1;
            } else {
                swap(array, parent, 2 * parent + 2);
                parent = 2 * parent + 2;
            }
        }
        if (2 * parent + 1 < array.size()) {
            swap(array, 2 * parent + 1, parent);
            parent = 2 * parent + 1;
        }
        array.remove(parent);
        return item;
    }

    public void printHeap() {
        if (array == null || array.size() == 0) {
            throw new IllegalArgumentException();
        }
        int level = 1;
        for (int i = 0; i < array.size(); ++i) {
            if (i == (int) (Math.pow(2, level) - 1)) {
                level++;
                System.out.println();
            }
            System.out.print(array.get(i).toString() + ' ');
        }
    }

    public void updateP(int index, E priority) {
        if (array == null || array.size() == 0 || index >= array.size()) {
            throw new IndexOutOfBoundsException();
        }
        array.set(index, priority);
        // no children:
        if (index * 2 + 1 > array.size() - 1) {
            while ((index - 1) / 2 >= 0 && priority.compareTo(array.get((index - 1) / 2)) < 0) {
                swap(array, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
            return;
        }
        // only leftChild:
        if (index * 2 + 1 == array.size() - 1) {
            if (priority.compareTo(array.get(index * 2 + 1)) > 0) {
                swap(array, index, index * 2 + 1);
                return;
            } else {
                while ((index - 1) / 2 >= 0 && priority.compareTo(array.get((index - 1) / 2)) < 0) {
                    swap(array, index, (index - 1) / 2);
                    index = (index - 1) / 2;
                }
                return;
            }
        }
        //have two children:
        if (priority.compareTo(array.get(index * 2 + 1)) > 0 || priority.compareTo(array.get(index * 2 + 2)) > 0) {
            while (2 * index + 2 < array.size() && (priority.compareTo(array.get(index * 2 + 1)) > 0 || priority.compareTo(array.get(index * 2 + 2)) > 0)) {
                if (array.get(2 * index + 1).compareTo(array.get(2 * index + 2)) < 0) {
                    swap(array, index, 2 * index + 1);
                    index = 2 * index + 1;
                } else {
                    swap(array, index, 2 * index + 2);
                    index = 2 * index + 2;
                }
            }
            return;
        } else {
            while ((index - 1) / 2 >= 0 && priority.compareTo(array.get((index - 1) / 2)) < 0) {
                swap(array, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    private void swap(ArrayList<E> array, int i, int j) {
        E item = array.get(i);
        array.set(i, array.get(j));
        array.set(j, item);
    }


    public static void main(String[] args){
        ArrayListImpHeap<Integer> heap = new ArrayListImpHeap<>();

//        heap.add(18);
//        heap.add(6);
//        heap.add(29);
//        heap.add(39);
//        heap.add(20);
//        heap.add(8);
//        heap.add(28);
//        System.out.println(heap.add(37));
//        heap.add(26);
//        System.out.println(heap.add(18));
//        heap.add(76);
//        heap.add(32);
//        heap.add(74);
//        heap.add(89);
//        heap.add(66);
//        heap.printHeap();
//        System.out.println();
//        System.out.println("--------------");

        try {
            heap.remove();
            heap.printHeap();
            System.out.println();
            System.out.println("--------------");
        } catch (IllegalArgumentException e) {
            System.out.println("Find an IllegalArgumentException: ");
            System.out.println("The heap is empty.");
        }

        try {
            heap.remove();
            heap.printHeap();
            System.out.println();
            System.out.println("--------------");
        } catch (IllegalArgumentException e) {
            System.out.println("Find an IllegalArgumentException: ");
            System.out.println("The heap is empty.");
        }

        try {
            heap.updateP(10, 6);
            heap.printHeap();
            System.out.println();
            System.out.println("--------------");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Find an IndexOutOfBoundsException: ");
            System.out.println("The index is out of boundary.");
        }

    }
}
