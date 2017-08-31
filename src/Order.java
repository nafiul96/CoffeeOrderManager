
import java.util.Objects;

/**
 * MD NAFIUL AZIM HOMEWORK-2 ID# 110548047
 *
 * @author nafi
 */
/**
 * This class creates Order type Object for the Beverage Store.
 *
 * @author Nafiul
 */
public class Order {

    private String order;
    private String specialInstruction;
    private double price;

    /**
     * Constructor which instantiates Order Object
     *
     * @param name - name of the Drink in Strings
     * @param price - Price of the Drink in doubles
     * @throws IllegalArgumentException - Thrown when name is empty or zero or
     * negative value is entered for price
     */
    public Order(String name, double price) throws IllegalArgumentException {

        if (price <= 0 || name.isEmpty()) {

            throw new IllegalArgumentException("Invalid input provided");
        } else {
            order = name;
            this.price = price;
        }
    }

    /**
     * Getter for the name of the Drink
     *
     * @return It returns the name of the Drink
     */
    public String getOrder() {
        return order;
    }

    /**
     * Setter for the name of the Order. It assigns the new name if the new name
     * is not empty, Otherwise does not change the old name.
     *
     * @param order - the new name
     */
    public void setOrder(String order) {
        if (!order.isEmpty()) {

            this.order = order;
        }

    }

    /**
     * Getter for Special Instruction
     *
     * @return - The details provided while creating order
     */
    public String getSpecialInstruction() {
        return specialInstruction;
    }

    /**
     * Setter for SpecialInstruction. Since this is optional, it does not check
     * for the emptiness of the new instruction
     *
     * @param specialInstruction - new special instrction
     */
    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    /**
     * Getter for the price
     *
     * @return the price of the beverage
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price. It checks for the zero and negative case for prices
     *
     * @param price - the new price of the order
     */
    public void setPrice(double price) {
        if (!(price <= 0)) {

            this.price = price;
        }
    }

    /**
     * Compares one order with another based on all the field variables
     *
     * @param obj
     * @return - It returns true if the orders are the same, otherwise returns
     * false
     */
    @Override
    public boolean equals(Object obj) {

        Order temp = (Order) obj;
        return this.order.equals(temp.order)
                && this.specialInstruction.equals(temp.specialInstruction)
                && this.price == temp.price;
    }

    /**
     * Makes deep copy of the Order Object
     *
     * @return - returns the newly copied object
     */
    @Override
    public Object clone() {

        Order temp = new Order(this.order, this.price);
        temp.specialInstruction = this.specialInstruction;
        return temp;
    }

    /**
     * Provides details about the order
     *
     * @return - String representing name,price, and instruction of the order
     */
    @Override
    public String toString() {

        return String.format("%11s%35s%10.2f", order, specialInstruction, price);
    }

}
