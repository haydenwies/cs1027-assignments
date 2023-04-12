/**
 * Class that uses the DLStackADT interface to create a stack object.
 * @author Hayden Wies
 */
public class DLStack<T> implements DLStackADT<T> {
    
    private DoubleLinkedNode<T> top;
    private int numItems;


    public DLStack() {
        this.top = null;
        this.numItems = 0;
    }


    /**
     * Push a piece of data to the top of the stack.
     * @param dataItem The piece of data being pushed to the stack.
     */
    public void push(T dataItem) {
        if (this.numItems == 0) {
            // If empty stack, just set top.
            this.top = new DoubleLinkedNode<T>(dataItem);
            this.numItems++;
        } else {
            // Otherwise set top and link with old top node.
            DoubleLinkedNode<T> newTop = new DoubleLinkedNode<T>(dataItem);
            this.top.setNext(newTop);
            newTop.setPrevious(this.top);
            this.top = newTop;
            this.numItems++;
        }
    }


    /**
     * Removes and returns the piece of data from the to of the stack.
     * @return The piece of data from the top of the stack.
     */
    public T pop() throws EmptyStackException {
        if (this.numItems > 0) {
            // Get element being returned and set new top to be next node.
            T returnVal = this.top.getElement();
            DoubleLinkedNode<T> newTop = this.top.getPrevious();
            if (newTop != null) {
                // If no other nodes, set top to null.
                newTop.setNext(null);
            }
            this.top = newTop;
            this.numItems--;
            return returnVal;
        } else {
            // Raise error if stack has no nodes.
            throw new EmptyStackException("The stack is empty.");
        }
    }


    /**
     * Removes and returns the kth piece of data from the stack.
     * @param k The position of the piece of data being removed from the stack (the top data piece is labeled as 1).
     * @return The kth piece of date from the stack.
     */
    public T pop(int k) throws InvalidItemException {
        if (k <= this.numItems) {
            int position = 1;
            DoubleLinkedNode<T> current = this.top;
            // Loop through list to find the desired position.
            while (position < k) {
                current = current.getPrevious();
                position++;
            }
            T returnVal = current.getElement();
            // Link next and previous nodes to eachother, removing current node from link.
            if (current.getPrevious() != null) {
                current.getPrevious().setNext(current.getNext());
            }
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.getPrevious());
            }
            if (k == 1 && this.numItems > 1) {
                // If param is 1 (top) and other nodes exist, set top to next node.
                this.top = this.top.getPrevious();
            }
            this.numItems--;
            return returnVal;
        } else {
            // Raise error if param is larger than size of stack.
            throw new InvalidItemException("Index value out of range of stack.");
        }
    }


    /**
     * Returns but does not remove the piece of data from the top of the stack.
     * @return The piece of data at the top of the stack.
     */
    public T peek() throws EmptyStackException {
        if (this.numItems > 0) {
            // Get element from top and return.
            T returnVal = this.top.getElement();
            return returnVal;
        } else {
            // Raise error if top is null.
            throw new EmptyStackException("The stack is empty.");
        }
    }


    /**
     * Checks if the stack in question is empty.
     * @return Value of whether or not the stack is empty.
     */
    public boolean isEmpty() {
        // Straightforward lol.
        if (this.numItems > 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Returns the number of items in the stack.
     * @return The number of items in the stack.
     */
    public int size() {
        // Also straightforward lol.
        return this.numItems;
    }


    /**
     * Returns the top node of the stack.
     * @return The top node of the stack.
     */
    public DoubleLinkedNode<T> getTop() {
        // Also straighforward lol.
        return this.top;
    }


    /**
     * Creates a string formatted as [data1 data2 data3 ...] containing the stack data.
     * @return The formatted string.
     */
    public String toString() {
        // Create string with starting value
        String returnString = "[ ";
        int position = 1;
        DoubleLinkedNode<T> current = this.top;
        // Loop through all elements, adding to the string
        while (position < this.numItems) {
            returnString += (current.getElement() + " ");
            current = current.getPrevious();
            position++;
        }
        // Add last piece of data with a closing bracket.
        returnString += (current.getElement() + "]");
        return returnString;
    }

}