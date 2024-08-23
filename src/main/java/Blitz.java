import java.util.Scanner;

public class Blitz{
    public static void main(String[] args) {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.trim().toLowerCase().equals("bye")) {
                break;
            }

            System.out.println("----------------\n" +
                    userInput + "\n" +
                    "----------------\n");
        }
        System.out.println("----------------\n" +
                "Till we meet again, GOODBYE!");
    }
}
