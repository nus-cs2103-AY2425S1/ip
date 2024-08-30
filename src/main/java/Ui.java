import java.util.ArrayList;
import java.util.Scanner;

public class Ui<T> {

    private static final String CHATBOT_NAME = "Easton";
    private Scanner scanner;

    Ui() {
        scanner = new Scanner(System.in);
    }

    private static void print(String string) {
        System.out.println(string);
    }

    public static void divider() {
        print("____________________________________________________________");
    }

    public static boolean welcome() {
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
        return false;
    }

    public static boolean goodbye() {
        print("Bye. Hope to see you again soon!");
        return true;
    }

    public String input() {
        return scanner.nextLine();
    }

    public void list(ArrayList<T> records) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < records.size(); i ++) {
            print((i + 1) + "." + records.get(i));
        }
    }

    public void show(T record) {
        print(record.toString());
    }

    public static void displayText(String text) {
        print(text);
    }
}
