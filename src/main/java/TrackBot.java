import java.util.Scanner;
public class TrackBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        System.out.println("**************************************");
        System.out.println("Hello from TrackBot!\n" + "How may I assist you?");

        // Reused from https://www.javatpoint.com/chatbot-application-in-java
        // with minor modifications
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("list")) {
                toDoList.listToString();
                //System.out.println(listString);
            } else if (userInput.equalsIgnoreCase("how are you?")) {
                System.out.println("TrackBot: I'm good, thank you. How about you?");
            } else if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else {
                String msg = toDoList.addToList(userInput);
                System.out.println(msg);
            }
        }
        // Reused code until here

        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("**************************************");

    }
}
