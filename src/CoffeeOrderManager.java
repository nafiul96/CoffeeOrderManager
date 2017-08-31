
import java.util.Scanner;

/**
 * MD NAFIUL AZIM HOMEWORK-2 ID# 110548047
 *
 * @author nafi
 */
/**
 * The main driver class contains main method
 *
 * @author nafi
 */
public class CoffeeOrderManager {
//this comment was added
    private static Scanner input = new Scanner(System.in);
    private static OrderList barista[] = new OrderList[3];
    private static int baristaNum = 1;
    private static Order clipBoard = null;

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {

            barista[i] = new OrderList();
        }
        System.out.println("Welcome to Star Duck");

        menuDriver();
    }

    /**
     * Menu Options
     */
    public static void mainMenu() {

        System.out.println("Main Menu:");
        System.out.println("O) Add Order");
        System.out.println("C) Cursor Options");
        System.out.println("P) Print All Orders");
        System.out.println("R)Reverse List");
        System.out.println("Q)Quit");
    }

    /**
     * drives the menu
     */
    public static void menuDriver() {

        boolean done = false;
        String s = "";
        int id = 0;
        int spot = 0;
        while (!done) {

            mainMenu();
            System.out.println("Select an option:");
            s = (input.nextLine()).toLowerCase();
            switch (s) {

                case "o":
                    placeOrder();
                    break;
                case "p":
                    System.out.println("Barista 1: ");
                    barista[1].printList();
                    System.out.println("Barista 2:");
                    barista[2].printList();
                    break;
                case "c":
                    cursorOperation();
                    break;
                case "r":
                    listReverse();
                    break;
                case "q":
                    done = true;
                    System.out.println("Thank you for using this Menu App");
                    break;
                default:
                    System.out.println("Not an Option. Try Again");

            }
        }
    }

    /**
     * reverses the list
     */
    public static void listReverse() {

        System.out.println("Reverse: Barista 1 or 2:");
        int num = input.nextInt();
        input.nextLine();
        barista[num].reverseList();
        System.out.println("Reversed Successfully");
    }

    /**
     * creates the order then uses addToList to add the order to the list
     */
    public static void placeOrder() {
        try {
            System.out.println("Drink Name: ");
            String name = input.nextLine();
            System.out.println("Special Instruction/Request:");
            String request = input.nextLine();
            System.out.println("Enter the Price: ");
            double price = input.nextDouble();
            Order entry = new Order(name, price);
            entry.setSpecialInstruction(request);
            addToList(entry);
        } catch (IllegalArgumentException e) {

            System.out.println(e);
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    /**
     * Add the item to the list
     *
     * @param item - the order to be added
     */
    public static void addToList(Order item) {
        try {
            System.out.println("Barista (1 or 2): ");
            int baristaNum = input.nextInt();
            input.nextLine();
            System.out.println("Where to add the Order: \n F-Front, B-Back, A- After Cursor, S-After Similar Item ");
            String opt = (input.nextLine()).toLowerCase();
            switch (opt) {

                case "f":
                    barista[baristaNum].appendToHead(item);
                    break;
                case "b":
                    barista[baristaNum].appendToTail(item);
                    break;
                case "a":
                    barista[baristaNum].insertAfterCursor(item);
                    break;
                case "s":
                    barista[baristaNum].insertAfterSimilar(item);
                    break;
                default:
                    System.out.println("Not an option! Try adding it again!");
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    /**
     * All the cursor options as print statements
     */
    public static void cursorMenu() {

        System.out.println("F- Forward");
        System.out.println("B- Backward");
        System.out.println("H- To Head");
        System.out.println("T- To tail");
        System.out.println("E- Edit");
        System.out.println("R- Remove");
        System.out.println("C- Cut Cursor");
        System.out.println("P- Paste");
    }

    /**
     * Performs all the cursor operations
     */
    public static void cursorOperation() {

        try {
            System.out.println("Select Cursor: ");
            int curNum = input.nextInt();
            input.nextLine();
            cursorMenu();
            System.out.println("Select an Option: ");
            String select = (input.nextLine()).toLowerCase();
            switch (select) {

                case "f":
                    barista[curNum].cursorForward();
                    break;
                case "b":
                    barista[curNum].cursorBackward();
                    break;
                case "h":
                    barista[curNum].resetCursorToHead();
                    break;
                case "t":
                    barista[curNum].resetCursorToTail();
                    break;
                case "e":
                    editCursor(curNum);
                    break;
                case "c":
                    cutCursor(curNum);
                    break;
                case "p":
                    barista[curNum].insertAfterCursor(clipBoard);
                    break;
                case "r":
                    barista[curNum].removeCursor();
                    break;
                default:
                    System.out.println("This is not an option");

            }
        } catch (EndOfListException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Cut the cursor and put it in the clipboard
     *
     * @param cursor
     */
    public static void cutCursor(int cursor) {
        try {
            clipBoard = barista[cursor].removeCursor();
        } catch (EndOfListException ex) {
            System.out.println(ex);
        }

    }

    /**
     * edits cursor
     *
     * @param cur - the specific cursor number
     */
    public static void editCursor(int cur) {

        System.out.println("N- Edit Name, D- Edit Price, I- Edit Request");
        String val = (input.nextLine()).toLowerCase();
        switch (val) {

            case "n":
                System.out.println("Please enter the new name");
                barista[cur].getCursorOrder().setOrder(input.nextLine());
                break;
            case "d":
                System.out.println("Please enter the new Price");
                barista[cur].getCursorOrder().setPrice(input.nextDouble());
                input.nextLine();
                break;
            case "i":
                System.out.println("Please enter new Special request to override the instruction before");
                barista[cur].getCursorOrder().setSpecialInstruction(input.nextLine());
            default:
                System.out.println("Not an option. Try again, Maybe!!! :(");
        }
    }

}
