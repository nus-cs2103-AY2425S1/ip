import java.util.Scanner;

public class Ratchet {
    static String INDENT = "    ";
    static String[] list = new String[100];
    static int listCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                displayList();
            } else if (input.equalsIgnoreCase("bye")) {
                exit();
            } else {
                addList(input);
            }
        }
    }

    private static void lineBreak() {
        System.out.println("   ________________________________________________________");
    }

    private static void greet() {
        lineBreak();
        System.out.println(INDENT + "Hello! I'm Ratchet\n" + INDENT + "What can I do for you?");
        lineBreak();
    }

    private static void exit() {
        lineBreak();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        lineBreak();
    }

    private static void addList(String text) {
        list[listCount++] = text;
        lineBreak();
        System.out.println(INDENT + "added: " + text);
        lineBreak();
    }

    private static void displayList() {
        lineBreak();
        for (int i = 0; i < listCount; i++) {
            System.out.println(INDENT + (i + 1) + ". " + list[i]);
        }
        lineBreak();
    }
}