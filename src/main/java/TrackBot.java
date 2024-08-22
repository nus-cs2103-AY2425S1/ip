import java.util.Scanner;
public class TrackBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        System.out.println("************************************************************");
        System.out.println("Hello from TrackBot!\n" + "How may I assist you?");

        // Reused from https://www.javatpoint.com/chatbot-application-in-java
        // with minor modifications
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                toDoList.listToString();
            } else if (userInput.equalsIgnoreCase("how are you?")) {
                System.out.println("I'm good, thank you. How about you?");
            } else if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                try {
                    int num = Integer.parseInt(userInput.substring(5)) - 1;
                    toDoList.markTask(num);
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                }

            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                try {
                    int num = Integer.parseInt(userInput.substring(7)) - 1;
                    toDoList.unmarkTask(num);
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                }

            } else if (userInput.toLowerCase().startsWith("todo ")) {
                try {
                    String taskDescription = userInput.substring(5).trim();
                    ToDo todo = new ToDo(taskDescription);
                    toDoList.addToList(todo);
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                }

            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                try {
                    String[] parts = userInput.substring(9).split(" /by ", 2);
                    Deadline deadline = new Deadline(parts[0], parts[1]);
                    toDoList.addToList(deadline);
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                }

            } else if (userInput.toLowerCase().startsWith("event ")) {
                try {
                    String[] parts = userInput.substring(6).split(" /from | /to ", 3);
                    Event event = new Event(parts[0], parts[1], parts[2]);
                    toDoList.addToList(event);
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                }

            } else {
                System.out.println("Sorry, I did not understand.");
            }
        }
        // Reused code until here

        // close scanner and exit with message
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }
}
