import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        System.out.println("""
                Hello! I'm Joe
                What can I do for you?""");
        Scanner scanner = new Scanner(System.in);
        String EXIT_WORD = "bye";
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals(EXIT_WORD)) {
                System.out.println("\tBye. Hope to see you again soon!");
                return;
            }
            System.out.println("\t" + userInput);
        }
    }
}
