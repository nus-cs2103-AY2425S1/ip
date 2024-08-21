import java.util.Scanner;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("> Hello! I'm %s.", NAME));
        System.out.println("> What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("> Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            if (current.trim().toLowerCase().equals("bye")) {
                break;
            }

            // echo user inputs
            System.out.println(HORIZONTAL_LINE);
            System.out.println(String.format("> %s", current));
            System.out.println(HORIZONTAL_LINE);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Alice alice = new Alice();

        alice.greet();
        alice.listen();
        alice.bye();
    }
}
