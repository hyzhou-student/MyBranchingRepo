import java.util.NoSuchElementException;

/**
An implementation of a queue as a circular array.
 */
public class CircularArrayQueue
{
    private Object[] elements;
    //private data
    private int currentSize;
    private int head;
    private int tail;

    /**
    Constructs an empty queue.
     */
    public CircularArrayQueue() {
        final int INITIAL_SIZE = 8;
        elements = new Object[INITIAL_SIZE];
        currentSize = head = tail = 0;
    }

    /**
    Checks whether this queue is empty.
    @return true if this queue is empty
     */
    public boolean empty() {return currentSize==0;}

    /**
    Adds an element to the tail of this queue.
    @param newElement the element to add
     */
    public void add(Object elem) {
        growIfNecessary();
        currentSize++;
        elements[tail] = elem;
        tail = (tail+1) % elements.length;
    }

    /**
    Removes an element from the head of this queue.
    @return the removed element
     */
    public Object remove() {
        if (currentSize == 0) {throw new NoSuchElementException();}
        currentSize--;
        Object elem = elements[head];
        elements[head] = null;
        head = (head+1) % elements.length;
        return elem;
    }

    /**
    Grows the element array if the current size equals the capacity.
     */
    private void growIfNecessary() {
        if (currentSize == elements.length) {
            Object[] newArray = new Object[elements.length*2];
            int idxOld = head;
            int idxNew = 0;
            do {
                newArray[idxNew] = elements[idxOld];
                idxOld = (idxOld+1) % elements.length;
                idxNew = idxNew + 1;
            } while (idxOld != tail);
            elements = newArray;
            head = 0;
            tail = idxNew;
            System.out.println(toString());
        }
    }
    
    public void firstToLast() {
        if (currentSize <= 1) {return;}
        
        elements[tail] = elements[head];
        if (head != tail) {
            elements[head] = null;
        }
        head = (head+1) % elements.length;
        tail = (tail+1) % elements.length;
    }
    
    public void lastToFirst() {
        if (currentSize <= 1) {return;}
        head = (head+elements.length-1) % elements.length;
        tail = (tail+elements.length-1) % elements.length;
        elements[head] = elements[tail];
        if (head != tail) {
            elements[tail] = null;
        }
    }
    
    public void addFirst(Object elem) {
        growIfNecessary();
        currentSize++;
        head = (head+elements.length-1) % elements.length;
        elements[head] = elem;
    }
    
    public void removeLast() {
        if (currentSize == 0) return;
        
        currentSize--;
        tail = (tail+elements.length-1) % elements.length;
        elements[tail] = null;
    }
    
    public int size() {
        return currentSize;
    }
    
    public String toString() {
        String str = "";
        if (currentSize==0) {return str;}
        int idx = head;
        str += elements[idx];
        idx = (idx+1) % elements.length;
        while (idx != tail) {
            str += ", " + elements[idx];
            idx = (idx+1) % elements.length;
        }
        return str;
    }
}//CircularArrayQueue
