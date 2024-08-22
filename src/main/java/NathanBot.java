import java.util.Scanner;

public class NathanBot {
    public static void main(String[] args) {
        String LINE = "____________________________________________________________\n";
        String GREET = """
                        Hello! I'm NathanBot
                        What can I do for you?
                       """;
        String EXIT = "Bye. Hope to see you again soon!\n";
        String BREAK_COMMAND = "bye";
        System.out.println(LINE + GREET + LINE);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals(BREAK_COMMAND)) {
                    System.out.println(LINE + EXIT + LINE);
                    break;
                } else {
                    System.out.println(LINE + input + "\n" + LINE);
                }
            }
        }
    }
}