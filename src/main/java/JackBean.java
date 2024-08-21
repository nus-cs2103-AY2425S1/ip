import java.util.Scanner;
import java.util.ArrayList;

public class JackBean {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. JackBean, signing off!";
    public static ArrayList<String> taskList = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);

        Scanner userInput = new Scanner(System.in);

        // GitHub Copilot helped to code this entire while loop (except personalisation)
        // and I have no idea how it knew what to do HAHAHA
        while (true) {
            String input = userInput.nextLine();

            if (input.equalsIgnoreCase("bye")) { // I added equalsIgnoreCase() by myself
                break;
            }

            switch (input) {
                case "list":
                    System.out.println(horizontalLine);
                    System.out.println("Yo homie!, here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(horizontalLine);
                    break;
                default:
                    taskList.add(input);
                    System.out.println(horizontalLine);
                    System.out.println("Got it homie! I've added this task: " + input);
                    System.out.println("Homie!, you have " + taskList.size() + " task(s) in the list now.");
                    System.out.println(horizontalLine);
                    break;
            }
        }

        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }
}
