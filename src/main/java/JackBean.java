import java.util.Scanner;

public class JackBean {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. JackBean, signing off!";

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
            System.out.println(horizontalLine);
            System.out.println("Wassup homie, you said this: " + input);
            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }
}
