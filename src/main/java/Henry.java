import java.util.Scanner;

public class Henry {

    /**
     * Prints greetings
     *
     */
    public static void greetings() {
        String greetings = "Hello! I'm Henry\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    /**
     * Prints exit
     *
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    public static void main(String[] args) {
        greetings();

        String[] tasks = new String[100];

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        do {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println();
                bye();
                break;
            } else if (input.equals("list")) {
                System.out.println();
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1
                            + ". "
                            + tasks[i]);
                }
                System.out.println();
            } else {
                tasks[index] = input;
                System.out.println("\n"
                        + "added: "
                        + input
                        + "\n");
                index++;
            }
        } while (true);
    }
}
