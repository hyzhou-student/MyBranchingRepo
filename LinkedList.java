import java.util.NoSuchElementException;
import java.util.ListIterator;

/**
A linked list is a sequence of nodes with efficient
element insertion and removal. This class
contains a subset of the methods of the standard
java.util.LinkedList class.
 */
public class LinkedList<T>
{
    private Node first;

    /**
    Constructs an empty linked list.
     */
    public LinkedList() {
        first = null;
    }

    /**
    Returns the first element in the linked list.
    @return the first element in the linked list
     */
    public T getFirst() {
        if (first == null) {throw new NoSuchElementException();}

        return first.data;
    }

    /**
    Removes the first element in the linked list.
    @return the removed element
     */
    public T removeFirst() {
        if (first == null) {throw new NoSuchElementException();}

        T removed = first.data;
        first = first.next;
        return removed;
    }

    /**
    Adds an element to the front of the linked list.
    @param element the element to add
     */
    public void addFirst(T elem) {
        Node newNode = new Node();
        newNode.data = elem;
        newNode.next = first;
        first = newNode;
    }

    public T get(int n) {
        Node node = first;
        if (node==null) {throw new NoSuchElementException();}
        for (int i=0; i<n; i++) {
            node = node.next;
            if (node==null) {throw new NoSuchElementException();}
        }
        return node.data;
    }
    
    public void set(int n, T elem) {
        Node node = first;
        if (node==null) {throw new NoSuchElementException();}
        for (int i=0; i<n; i++) {
            node = node.next;
            if (node==null) {throw new NoSuchElementException();}
        }
        node.data = elem;
    }
    
    public boolean contains(T o) {
        Node n = first;
        while (n != null) {
            if (n.data.equals(o)) {
                return true;
            }
            n = n.next;
        }
        return false;
    }
    
    public void reverse() {
        Node node = first;
        Node prev = null;
        while (node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        first = prev;
    }

    /**
    Returns an iterator for iterating through this list.
    @return an iterator for iterating through this list
     */
    public ListIterator listIterator() {
        return new LinkedListIterator();
    }

    public int size() {
        int size = 0;
        Node n = first;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }

    public int rsize() {
        return rsize(first);
    }

    public int rsize(Node start) {
        if (start == null) {return 0;}
        return rsize(start.next) + 1;
    }

    //Class Node
    class Node {
        public T data;
        public Node next;
    }

    class LinkedListIterator<E> implements ListIterator<E>
    {
        //private data
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        /**
        Constructs an iterator that points to the front
        of the linked list.
         */
        public LinkedListIterator() {
            position = null;
            previous = null;
            isAfterNext = false;
        }

        /**
        Moves the iterator past the next element.
        @return the traversed element
         */
        public E next() {
            if (!hasNext()) {throw new NoSuchElementException();}

            previous = position;
            isAfterNext= true;

            if (position == null) {
                position = first;
            }
            else {
                position = position.next;
            }

            return (E) position.data;
        }

        /**
        Tests if there is an element after the iterator position.
        @return true if there is an element after the iterator position
         */
        public boolean hasNext() {
            if (position == null)
                return first != null;
            else
                return position.next != null;
        }

        /**
        Adds an element before the iterator position
        and moves the iterator past the inserted element.
        @param element the element to add
         */
        public void add(E elem){
            if (position == null) {
                addFirst((T) elem);
                position = first;
            }
            else {
                Node node = new Node();
                node.data = (T) elem;
                node.next = position.next;
                position.next = node;
                position = node;
            }
            isAfterNext = false;
        }

        /**
        Removes the last traversed element. This method may
        only be called after a call to the next() method.
         */
        public void remove() {
            if (!isAfterNext) {throw new IllegalStateException();}

            if (position == first) {removeFirst();}
            else {
                previous.next = position.next;
            }
            position = previous;

            isAfterNext = false;

            //first call to remove the current position reverts to the predecessor of removed element
            //thus predecessor is no longer known
        }

        /**
        Sets the last traversed element to a different value.
        @param element the element to set
         */
        public void set (E elem) {
            if (!isAfterNext) {throw new IllegalStateException();}
            position.data = (T) elem;
        }

        public int nextIndex() {return 0;}

        public int previousIndex() {return 0;}

        public boolean hasPrevious() {return true;}

        public E previous() {return null;}
    }//LinkedListIterator
}//LinkedList
