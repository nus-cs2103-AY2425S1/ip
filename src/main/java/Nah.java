import java.util.Scanner;

public class Nah {
    static String greetLine = "____________________________________________________________\n"
            + " Hello! I'm NAH \n"
            + " What can I do for you? \n"
            + "____________________________________________________________\n";
    static String byeLine = " Bye. Hope to see you again soon!\n";
    void greet() {
        System.out.println(greetLine);
    }

    void exit() {
        System.out.println(byeLine);
    }
    public static void main(String[] args) {
        String logo = "| \\   | |      /\\      | |  | | \n"
                + "| |\\  | |     / /\\     | |==| | \n"
                + "| | \\ | |    / /__\\    | |  | | \n"
                + "| |  \\  |   / /    \\   | |  | | \n";


        System.out.println("Hello from\n" + logo);

        Nah nah = new Nah();

        nah.greet();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________ \n");
            if (input.equals("bye")) {
                nah.exit();
                break;
            }

            else {
                System.out.println(" " + input + "\n");
            }
        }
        System.out.println("____________________________________________________________ \n");
    }
}
