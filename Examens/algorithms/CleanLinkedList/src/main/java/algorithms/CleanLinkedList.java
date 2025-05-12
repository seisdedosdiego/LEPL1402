package algorithms;


/**
 * Question:
 *
 * You are asked to clean a increasing sorted linked List (see the TODO below)
 * Cleaning the linkedList means keeping only one occurrence of each value.
 *
 * For instance cleaning: 3,3,3,4,5,5,6,6,6,7,9,9,9,9,10,10
 * Gives: 3,4,5,6,7,9,10
 *
 * Your algorithm should execute in Theta(n)
 * where n are the number of elements in the original list
 *
 */
public class CleanLinkedList {

    Node first = null;
    Node last = null;

    public void add(int v) {
        Node newNode = new Node(v, null);

        if (first == null) {
            first = newNode;
            last = newNode; 
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public void add(int ... values) {
        for (int v: values) {
            add(v);
        }
    }


    /**
     * Given the increasingly sorted list, it removes the duplicates
     * @return an increasingly sorted list containing the same set
     *         of elements as list but without duplicates.
     */
    public CleanLinkedList clean() {
        CleanLinkedList clean = new CleanLinkedList();
        clean.add(first.v);

        Node currentNode = first.next;
        int currentValue = first.v;

        while (currentNode != null) {
            if (currentValue != currentNode.v) {
                clean.add(currentNode.v);
                currentValue = currentNode.v;
            }
            currentNode = currentNode.next;
        }

        return clean;
    }


    class Node {
        int v;
        Node next;
        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }
    
}

