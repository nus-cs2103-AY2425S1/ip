import java.util.Scanner;

public class Fred {
    static String line = "li____________________________________________________________";
    static String name = "Fred";
    public static void main(String[] args) {
        greet();
        echo();
        sayFarewell();
        exit();
    }

    private static void greet() {
        String greeting = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    private static void sayFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);
    }

    private static void exit(){
        System.exit(0);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        sayFarewell();
        exit();
    }
}
