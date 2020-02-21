package Homework570;


import java.util.Random;
import java.util.Stack;
/**
 * @author Xinyi Zhao
 * */
public class Treap<E extends Comparable<E>> {

    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        //Constructors
        /**
         * This constructor creates a new node with the given key and priority.
         * The pointers to child nodes are initialized to be null.
         * @param data    The key of the new node
         * @param priority    The heap priority of the new node
         * @throws NullPointerException    Exceptions if data is null
         * */
        public Node(E data, int priority) {
            if (data == null) {
                throw new NullPointerException();
            }
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        //Methods
        /**
         * The rotation methods preserve the ordering of the BST,
         * but allow one to restore the heap invariant.
         * @return root    a reference to the root of the result
         * */
        Node<E> rotateRight() {
            Node<E> root = this.left;
            this.left = root.right;
            root.right = this;
            return root;
        }

        Node<E> rotateLeft() {
            Node<E> root = this.right;
            this.right = root.left;
            root.left = this;
            return root;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[" + data + ", " + priority + "]");
            return sb.toString();
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    //Constructors
    /** The constructors create an empty Treap. */
    public Treap() {
        this.priorityGenerator = new Random();
        this.root = null;
    }

    public Treap(long seed) {
        this.priorityGenerator = new Random(seed);
        this.root = null;
    }

    //Methods
    /**
     * This method creates a new node.
     * @param key    The key of the added node
     * @return true    If the node was successfully added to the Treap
     * @return false   If exist a node in the Treap containing the same key
     * */
    boolean add(E key) {
        int priority = priorityGenerator.nextInt();
        return add(key, priority);
    }

    boolean add(E key, int priority) {
        if (this.root == null) {
            this.root = new Node<E>(key, priority);
            return true;
        }
        if (find(key)) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        Node<E> newNode = new Node<>(key, priority);
        Node<E> node = root;
        while (node != null) {
            stack.push(node);
            if (key.compareTo(node.data) < 0) {
                if (node.left == null) {
                    node.left = newNode;
                    break;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    node.right = newNode;
                    break;
                } else {
                    node = node.right;
                }
            }
        }

        if (priority > stack.peek().priority) {
            reheap(stack, newNode);
        }

        return true;
    }

    private void reheap(Stack<Node> stack, Node<E> newNode) {
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            if (newNode.priority > node.priority) {
                if (newNode.data.compareTo(node.data) < 0) {
                    if (!stack.isEmpty()) {
                        Node<E> tmp = stack.peek();
                        if (node.data.compareTo(tmp.data) > 0) {
                            tmp.right = node.rotateRight();
                        } else {
                            tmp.left = node.rotateRight();
                        }
                    } else {
                        this.root = node.rotateRight();
                        return;
                    }
                } else {
                    if (!stack.isEmpty()) {
                        Node<E> tmp = stack.peek();
                        if (node.data.compareTo(tmp.data) > 0) {
                            tmp.right = node.rotateLeft();
                        } else {
                            tmp.left = node.rotateLeft();
                        }
                    } else {
                        this.root = node.rotateLeft();
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    /**
     * This method deletes a node with the given key
     * @param key    The target being sought
     * @return true    If the node is removed successfully
     * @return false   If the given key was not found
     * */
    boolean delete(E key) {
        if (!find(key)) {
            return false;
        }

        Node<E> node = this.root;
        Node<E> parent = this.root;

        if (key.equals(this.root.data)) {
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
                return true;
            }
            if (this.root.left != null && this.root.right != null) {
                if (this.root.left.priority > this.root.right.priority) {
                    this.root = node.rotateRight();
                    parent = this.root;
                } else {
                    this.root = node.rotateLeft();
                    parent = this.root;
                }
            } else if (this.root.left != null) {
                this.root = this.root.left;
                return true;
            } else {
                this.root = this.root.right;
                return true;
            }
        }

        while (parent != null) {
            if (key.compareTo(parent.data) < 0) {
                node = parent.left;
            } else {
                node = parent.right;
            }
            if (key.equals(node.data)) {
                break;
            } else {
                parent = node;
            }
        }

        while (node.left != null && node.right != null) {
            if (node.left.priority > node.right.priority) {
                if (parent.data.compareTo(key) > 0) {
                    parent.left = node.rotateRight();
                } else {
                    parent.right = node.rotateRight();
                }
            } else {
                if (parent.data.compareTo(key) > 0) {
                    parent.left = node.rotateLeft();
                } else {
                    parent.right = node.rotateLeft();
                }
            }
        }

        if (node.right != null) {
            if (parent.data.compareTo(key) > 0) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
            return true;
        }

        if (node.left != null) {
            if (parent.data.compareTo(key) > 0) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
            return true;
        }

        if (parent.data.compareTo(key) > 0) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        return true;
    }

    /**
     * This method finds a node with the given key in the treap rooted at root
     * @param key    The target being sought
     * @return true    If the target being found
     * @return false    otherwise
     * */
    private boolean find(Node<E> root, E key) {
        Node<E> node = root;
        while (node != null) {
            if (node.data.equals(key)) {
                return true;
            }
            if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public boolean find(E key) {
        Node<E> node = this.root;
        while (node != null) {
            if (node.data.equals(key)) {
                return true;
            }
            if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preorder(this.root, sb);
        return sb.toString();
    }

    /**
     * This methods implements a preorder traversal of the treap
     * and output a representation of the nodes as a String.
     * */
    private void preorder(Node<E> root, StringBuilder sb) {
        if (root == null) {
            sb.append("null" + "\n");
            return;
        }
        Node<E> node = root;
        sb.append(node.toString() + "\n");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    //test:
    /** The main() method*/
    public static void main(String[] args) {

        Treap<Integer> testTree = new Treap <Integer>();

        //test add:
        try {
            testTree.add(4,19);
            testTree.add(2,31);
            testTree.add(6,70);
            testTree.add(1,84);
            testTree.add(3,12);
            testTree.add(5,83);
            testTree.add(7,26);
            System.out.println(testTree.add(1));

            System.out.println(testTree.toString());
            System.out.println("--------");

            testTree.add(null, -1);

        } catch (NullPointerException e) {
            System.out.println("Find a NullPointerException: " + "key cannot be null");
        }


        Treap testTreeWithSeed = new Treap<Integer>(100);
        try {
            testTreeWithSeed.add(4, 19);
            testTreeWithSeed.add(2, 31);
            testTreeWithSeed.add(6, 70);
            testTreeWithSeed.add(4, 20);
            testTreeWithSeed.add(1, 84);
            testTreeWithSeed.add(3, 12);
            testTreeWithSeed.add(5, 83);
            testTreeWithSeed.add(7, 26);
            testTreeWithSeed.add(10);
            testTreeWithSeed.add(50);
            testTreeWithSeed.add(-6);

            System.out.println(testTreeWithSeed.add(10));

            System.out.println(testTreeWithSeed.toString());
            System.out.println("--------");

            testTreeWithSeed.add(null, 100);

        } catch (NullPointerException e) {
            System.out.println("Find a NullPointerException: " + "key cannot be null");
        }



        //test delete:
        try {
            System.out.println(testTree.delete(1));
            System.out.println(testTree.delete(20));
            System.out.println(testTree.toString());
            System.out.println("--------");

            System.out.println(testTree.delete(null));

        } catch (NullPointerException e) {
            System.out.println("Find a NullPointerException: " + "key cannot be null");
        }


        //test find:
        try {
            System.out.println(testTree.find(3));
            if (testTree.find(0)) {
                System.out.println("Find the node of key - 0.");
            } else {
                System.out.println("Not find the node of key - 0");
            }

            if (testTree.find(2)) {
                System.out.println("Find the node of key - 2.");
            } else {
                System.out.println("Not find node of key - 2.");
            }

            testTree.find(null);
        } catch (NullPointerException e) {
            System.out.println("Find a NullPointerException: " + "key cannot be null");
        }

    }
}
