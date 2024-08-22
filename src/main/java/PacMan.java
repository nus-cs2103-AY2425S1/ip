import java.util.ArrayList;
import java.util.Scanner;

public class PacMan {
    private static final ArrayList<String> list = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm PacMan");
        System.out.println("How can I help you?");
    }

    private static void exit() {
        System.out.println("Good bye. Hope to see you soon!");
    }

    private static void echo(String text) {
        System.out.println(text);
    }

    private static void addList(String item) {
        list.add(item);
    }

    private static void printList() {
        for (int index = 1; index <= list.size(); index = index + 1) {
            System.out.println(index + ". " + list.get(index - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                echo(input);
                addList(input);
            }
        }
        exit();
    }
}
