import java.util.Scanner;

public class ZBot {
    private static final String[] cache = new String[100];
    private static int cacheIndex = 0;

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listText();
            } else {
                storeText(input);
            }

            input = sc.nextLine();
        }

        sc.close();
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm ZBot!");
        System.out.println("What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void storeText(String input) {
        cache[cacheIndex] = input;
        cacheIndex = (cacheIndex + 1) % 100;
        System.out.println(String.format("added: %s\n", input));
    }

    public static void listText() {
        for (int i = 0; i < cache.length; i++) {
            if (cache[i] != null) {
                System.out.println(String.format("%d. %s", i + 1, cache[i]));
            }
        }
        System.out.println();
    }
}
