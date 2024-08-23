import java.util.Scanner;

public class Blitz{
    public static void main(String[] args) {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equalsIgnoreCase("bye")) {
            userInput = scanner.nextLine();
            System.out.println("----------------\n" +
                    userInput + "\n" +
                    "----------------\n");
        }
        System.out.println("----------------\n" +
                "Till we meet again, GOODBYE!");
    }
}
