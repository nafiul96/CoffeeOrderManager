
/**
 * MD NAFIUL AZIM
 * HOMEWORK-2
 * ID# 110548047
 *
 * @author nafi
 */
/**
 * It create node for the OrderList linking two adjescent lists as in doubly linked list
 *
 * @author Nafiul
 */
public class OrderListNode {

    private Order data;
    private OrderListNode next;
    private OrderListNode prev;

    /**
     * Creates the OrderListNode with specific Order
     *
     * @param initOrder - the new order
     * @throws IllegalArgumentException - assures that the order is not null
     */
    public OrderListNode(Order initOrder) throws IllegalArgumentException {

        if (initOrder == null) {

            throw new IllegalArgumentException("Can't add this order");
        } else {

            this.data = initOrder;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Getter for data of the node
     *
     * @return - Returns the data Order that the node contains
     */
    public Order getData() {
        return data;
    }

    /**
     * Setter for Data field
     *
     * @param data - new value of data of the node which is the new order for
     * the node overriding the existing order even the new order is null.
     */
    public void setData(Order data) {
        this.data = data;
    }

    /**
     * Gets the next node reference
     *
     * @return - the node next to the current node
     */
    public OrderListNode getNext() {
        return next;
    }

    /**
     * Establishes the next link to the node
     *
     * @param next - the node that should be next to the current node
     */
    public void setNext(OrderListNode next) {
        this.next = next;
    }

    /**
     * Getter for the previous link to the current node
     *
     * @return - returns the reference to the previous link to the current node
     */
    public OrderListNode getPrev() {
        return prev;
    }

    /**
     * setter for the previous link
     *
     * @param prev - the node that is going to be previous to the current node
     */
    public void setPrev(OrderListNode prev) {
        this.prev = prev;
    }

}
