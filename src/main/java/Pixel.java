import java.util.Scanner;

public class Pixel {
    public static String LINE = "\t" + "------------------------------------";
    public static void greeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void response() {
        while (true) {
            Scanner scanner = new Scanner(System.in); // Creating a scanner object
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    System.out.println(LINE);
                    System.out.println("\t" + "list");
                    System.out.println(LINE);
                    break;
                case "blah":
                    System.out.println(LINE);
                    System.out.println("\t" + "blah");
                    System.out.println(LINE);
                    break;
                case "bye":
                    exit();
                    break;
            }
            if (command.equals("bye")) break;
        }
    }
    public static void main(String[] args) {
        greeting();
        response();
    }
}
