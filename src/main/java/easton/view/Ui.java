package easton.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Represents the user interface of the program.
 */
public class Ui<T> {

    private static final String CHATBOT_NAME = "easton.Easton";
    private Scanner scanner;

    /**
     * Construct a new user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    private static void print(String string) {
        System.out.println(string);
    }

    public static void divider() {
        print("____________________________________________________________");
    }

    /**
     * Prints welcome message.
     */
    public static void welcome() {
        String logo = " _______  _______  _______  _______  _______  __    _\n"
                + "|       ||   _   ||       ||       ||       ||  |  | |\n"
                + "|    ___||  |_|  ||  _____||_     _||   _   ||   |_| |\n"
                + "|   |___ |       || |_____   |   |  |  | |  ||       |\n"
                + "|    ___||       ||_____  |  |   |  |  |_|  ||  _    |\n"
                + "|   |___ |   _   | _____| |  |   |  |       || | |   |\n"
                + "|_______||__| |__||_______|  |___|  |_______||_|  |__|\n";
        print("Hello from\n" + logo);
        divider();
        print("Hello! I'm " + CHATBOT_NAME);
        print("What can I do for you?");
        divider();
    }

    /**
     * Prints goodbye message.
     */
    public static void goodbye() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the input from the user interface.
     * @return The input from the user.
     */
    public String input() {
        return scanner.nextLine();
    }

    /**
     * Prints the list.
     *
     * @param records Records to be printed.
     * @param predicate Predicate to filter the list of records.
     */
    public void list(ArrayList<T> records, Predicate<T> predicate) {
        for (int i = 0; i < records.size(); i++) {
            if (predicate.test(records.get(i))) {
                print((i + 1) + "." + records.get(i));
            }
        }
    }

    /**
     * Shows a record.
     *
     * @param record Record to be printed.
     */
    public void show(T record) {
        print(record.toString());
    }

    /**
     * Prints a given text.
     *
     * @param text Text to be printed
     */
    public static void displayText(String text) {
        print(text);
    }
}
