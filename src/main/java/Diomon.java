import java.util.Scanner;

public class Diomon {
    private static void greeting() {
        String greetingMessage = """
                           ________________________________________________________________
                           Hello! I'm Diomon
                           What do you need recorded?
                           ________________________________________________________________
                           """;
        System.out.print(greetingMessage);
    }
    private static void echo() {
        // Initialise instance
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        greeting();
        while(true) {
            String input = scanner.nextLine();
            System.out.println("________________________________________________________________");
            Commands.run(input,taskList);
            System.out.println("________________________________________________________________");
        }
    }

    public static void main(String[] args) {
        echo();
    }
}
