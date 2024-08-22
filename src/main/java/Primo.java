import java.util.Scanner;

public class Primo {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean ended = false;

    private static void sayBye() {
        String byeMessage = "El Primo: " +
                            "Bye. Hope to see you again soon!\n";
        System.out.println(byeMessage);
        ended = true;
    }

    public static void assessInput(String input) {
        if (input.equals("bye")) {
            sayBye();
        } else {
            String output = "El Primo: " + input + "\n";
            System.out.println(output);
        }
    }
    public static void readInput() {
        System.out.println("Me: ");
        String input = scanner.nextLine();
        assessInput(input);
    }

    public static void main(String[] args) {
        System.out.println("""
                El Primo: Hello! I'm El Primo!!
                El Primo: What can I do for you?
                """);

        while (!ended) {
            readInput();
        }
    }
}
