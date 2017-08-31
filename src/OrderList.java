
/**
 * MD NAFIUL AZIM
 * HOMEWORK-2
 * ID# 110548047
 *
 * @author nafi
 */
/**
 * This the class that contains the actual LinkedList Object. It contains reference to head, tail and to make it
 * easy to traverse, we also have cursor!
 *
 * @author nafi
 */
public class OrderList {

    private OrderListNode head, tail, cursor;
    int size;

    /**
     * Instantiate all the field variables as null; The default constructor
     */
    public OrderList() {

        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Counts the number of Orders in the specific list
     *
     * @return
     */
    public int numOrder() {

        return size;
    }

    /**
     * Getter for the order in the cursor
     *
     * @return - It returns the order in the cursor even when the cursor is null
     * which is not the case. So we don't even bother about the case
     */
    public Order getCursorOrder() {

        return this.cursor.getData();
    }

    /**
     * Resets the cursor to the head of the list
     */
    public void resetCursorToHead() {

        cursor = head;
    }

    /**
     * It resets the cursor to the end of the list
     */
    public void resetCursorToTail() {

        cursor = tail;
    }

    /**
     * Moves the cursor forward until it reaches the end of the list
     *
     * @throws EndOfListException - assures that the cursor does not go beyond
     * the tail which will make it null.
     */
    public void cursorForward() throws EndOfListException {

        if (cursor == tail) {

            throw new EndOfListException();
        } else {

            cursor = cursor.getNext();
        }
    }

    /**
     * Moves the cursor backward till it reaches the head
     *
     * @throws EndOfListException - assures that the cursor does not go beyond
     * the head since we don't want to worry about cursor being null
     */
    public void cursorBackward() throws EndOfListException {

        if (cursor == head) {

            throw new EndOfListException();
        } else {

            cursor = cursor.getPrev();
        }
    }

    /**
     * It inserts a complete order after the cursor
     *
     * @param newOrder - the new order to be added to the list
     * @throws IllegalArgumentException - checks to see that we are not adding
     * null order/ incomplete order.
     */
    public void insertAfterCursor(Order newOrder) throws IllegalArgumentException {

        OrderListNode temp;
        if (newOrder == null) {

            throw new IllegalArgumentException("OOps! Looks like you haven't created the order.");
        } else if (cursor == null) {

            temp = new OrderListNode(newOrder);
            cursor = tail = head = temp;
        } else if (cursor == tail) {

            appendToTail(newOrder);
            cursor = tail;
        } else {

            temp = new OrderListNode(newOrder);
            temp.setNext(cursor.getNext());
            temp.setPrev(cursor);
            cursor.getNext().setPrev(temp);
            cursor.setNext(temp);
            cursor = temp;
        }
    }

    /**
     * It adds the order to the front of the list
     *
     * @param newOrder - the new order to be added to the list
     * @throws IllegalArgumentException - assures that we are not adding null
     * order.
     */
    public void appendToHead(Order newOrder) throws IllegalArgumentException {

        if (newOrder == null) {

            throw new IllegalArgumentException("Looks like the order is not there");
        } else if (head == null) {

            insertAfterCursor(newOrder);
        } else {

            OrderListNode temp = new OrderListNode(newOrder);
            temp.setNext(head);
            head.setPrev(temp);
            head = temp;
            resetCursorToHead();
        }
    }

    /**
     * Adds Order to the back of the list
     *
     * @param newOrder - the order to be added
     * @throws IllegalArgumentException - assures the new order is not null
     */
    public void appendToTail(Order newOrder) throws IllegalArgumentException {

        OrderListNode temp;

        if (newOrder == null) {

            throw new IllegalArgumentException("OOPs! Looks like you haven't create the order");
        } else if (tail == null) {

            temp = new OrderListNode(newOrder);
            cursor = head = tail = temp;
        } else if (tail != null) {

            temp = new OrderListNode(newOrder);
            temp.setPrev(tail);
            tail.setNext(temp);
            tail = temp;
        }
    }

    /**
     * Add the new order to the order exactly same as it. If no similar order is
     * found, it will be appended to the back by default
     *
     * @param newOrder - the order to be added
     * @throws IllegalArgumentException - assures that the new order is not null
     */
    public void insertAfterSimilar(Order newOrder) throws IllegalArgumentException {

        if (newOrder == null) {

            throw new IllegalArgumentException("Looks like the order is not there");
        } else if (head == null) {

            insertAfterCursor(newOrder);
        } else {

            boolean find = false;
            while (cursor.getNext() != null && !find) {

                if (!newOrder.equals(cursor.getData())) {
                    cursor = cursor.getNext();
                } else {

                    find = true;
                }
            }

            if (!find) {
                System.out.println("inside condition");
                appendToTail(newOrder);
            } else {

                insertAfterCursor(newOrder);
            }
        }
    }

    /**
     * Removes the cursor order while the other orders in the list are intact
     *
     * @return- the order from the cursor that has been removed
     * @throws EndOfListException - assures that we are not removing cursor from
     * list when the list is empty
     */
    public Order removeCursor() throws EndOfListException {

        Order temp = null;
        if (cursor == null) {

            throw new EndOfListException();
        } else if (cursor.getNext() == null || cursor.getPrev() == null) {

            if (cursor == tail && cursor == head) {
                temp = cursor.getData();
                cursor = head = tail = null;
            } else if (cursor == tail && cursor != head) {

                temp = cursor.getData();
                cursor = cursor.getPrev();
                cursor.setPrev(null);
            }else if(cursor == head){
            
                head = head.getNext();
                head.setPrev(null);
            }
        } else {

            temp = cursor.getData();
            cursor = cursor.getPrev();
            cursor.getNext().getNext().setPrev(cursor);
            cursor.setNext(cursor.getNext().getNext());

        }
        return temp;
    }

    /**
     * Reverses the list
     */
    public void reverseList() {

        OrderList reversal = new OrderList();
        OrderListNode nodeptr = head;
        while (nodeptr != null) {

            reversal.appendToHead((Order) nodeptr.getData().clone());
            nodeptr = nodeptr.getNext();
        }
        this.head = reversal.head;

    }

    /**
     * Merges two list by picking elements alternatively
     *
     * @param one - destination list
     * @param two - source list
     * @return - merges two list into the destinition list
     */
    public static OrderList mergeList(OrderList one, OrderList two) {
        OrderList merge = new OrderList();
        try {

            one.resetCursorToHead();
            two.resetCursorToHead();
            while (!(one.cursor.getNext() == null && two.cursor.getNext() == null)) {

                if (one.cursor == null && two.cursor != null) {

                    //merge.insertAfterCursor(one.);
                    merge.insertAfterCursor((Order) two.cursor.getData().clone());
                    two.cursorForward();
                } else if (two.cursor == null && one.cursor != null) {

                    merge.insertAfterCursor((Order) one.cursor.getData().clone());
                    one.cursorForward();
                } else {

                    merge.insertAfterCursor((Order) one.cursor.getData().clone());
                    merge.insertAfterCursor((Order) two.cursor.getData().clone());
                    one.cursorForward();
                    two.cursorForward();
                }

            }

        } catch (EndOfListException e) {

            System.out.println(e);
        } finally {

            return merge;
        }

    }

    /**
     * Prints out the entire list
     */
    public void printList() {

        // System.out.printf("",);
        System.out.println("List");
        OrderListNode temp = head;
        System.out.printf("%20s%35s%10s \n", "Order", "Instruction", "Price");
        System.out.println("----------------------------------------------------------------------");
        if (this.head == null) {
            System.out.println("[Empty]");
        } else {
            while (temp != null) {
                if (temp == cursor) {

                    System.out.println("=>*" + temp.getData());
                } else {
                    System.out.println(temp.getData());
                }
                temp = temp.getNext();
            }
        }

    }

}
