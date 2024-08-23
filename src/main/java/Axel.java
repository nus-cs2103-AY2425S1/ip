import java.util.Scanner;
public class Axel {
    private static final int MAX_TEXTS_IN_LIST = 100;
    private static String[] textList = new String[MAX_TEXTS_IN_LIST];
    private static int textListCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        //Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Axel");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        //Repeatedly prompts for user's input
        while (true) {
            //Reads user's input
            userInput = scanner.nextLine();
            //Display texts entered
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                displayTextList();
                System.out.println("____________________________________________________________");
            //Exit message
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                //Acknowledges that the data has been stored
                addText(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void addText(String text) {
        if (textListCount < MAX_TEXTS_IN_LIST) {
            textList[textListCount] = text;
            textListCount++;
        } else {
            System.out.println("Text list is full!");
        }
    }

    private static void displayTextList() {
        if (textListCount == 0) {
            System.out.println("No texts in the list!");
        } else {
            for (int i = 0; i < textListCount; i++) {
                System.out.println((i + 1) + ". " + textList[i]);
            }
        }
    }
}
