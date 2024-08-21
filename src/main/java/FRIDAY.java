import java.util.Scanner;

public class FRIDAY {
    public static void main(String[] args) {
        //divider
        String divider = "";
        for (int i = 0; i < 40; i++) {
            divider += "-";
        }
        divider += "\n";

        //preset messages
        String greeting = divider + "Hello! I'm FRIDAY\nWhat can I do for you?\n" + divider;
        String exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;

        //variable to track state of bot
        Boolean isActive = true;

        //initialize scanner object
        Scanner scanner = new Scanner(System.in);

        //variable to store user input
        String userInput = "";

        //variable to store output
        String output = "";

        System.out.println(greeting);
        //running the chatbot
        while(isActive) {
            userInput = scanner.nextLine();
            //the keyword bye triggers exit bot
            if(userInput.toLowerCase().equals("bye")) {
                System.out.println(exitMessage);
                isActive = false;
                break;
            }
            System.out.println(divider  + userInput + "\n" + divider);
        }
    }
}
