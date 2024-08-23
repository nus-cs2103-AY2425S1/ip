import java.util.Scanner;
public class TrackBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        System.out.println("************************************************************");
        System.out.println("Hello from TrackBot!\n" + "How may I assist you?");

        //Solution below inspired by https://www.javatpoint.com/chatbot-application-in-java
        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    toDoList.listToString();
                } else if (userInput.equalsIgnoreCase("how are you?")) {
                    System.out.println("I'm good, thank you. How about you?");
                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else if (userInput.toLowerCase().startsWith("mark")) {
                    String input = userInput.substring(4).trim();
                    if (input.isEmpty()) {
                        System.out.println("Correct usage: mark <task number>");
                    } else {
                        try {
                            int num = Integer.parseInt(input) - 1;
                            toDoList.markTask(num);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid task number.");
                        }
                    }
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    String input = userInput.substring(6).trim();
                    if (input.isEmpty()) {
                        System.out.println("Correct usage: unmark <task number>");
                    } else {
                        try {
                            int num = Integer.parseInt(input) - 1;
                            toDoList.unmarkTask(num);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid task number.");
                        }
                    }
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    String taskDescription = userInput.substring(4).trim();
                    if (taskDescription.isEmpty()) {
                        throw TrackBotException.invalidFormat("todo", "todo <task description>");
                    }
                    ToDo todo = new ToDo(taskDescription);
                    toDoList.addToList(todo);
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    String[] parts = userInput.substring(8).trim().split(" /by ", 2);
                    if (parts.length < 2) {
                        throw TrackBotException.invalidFormat("deadline", "deadline <task description> /by <date/time>");
                    }
                    Deadline deadline = new Deadline(parts[0], parts[1]);
                    toDoList.addToList(deadline);
                } else if (userInput.toLowerCase().startsWith("event")) {
                    String[] parts = userInput.substring(5).trim().split(" /from | /to ", 3);
                    if (parts.length < 3) {
                        throw TrackBotException.invalidFormat("event", "event <description> /from <start> /to <end>");
                    }
                    Event event = new Event(parts[0], parts[1], parts[2]);
                    toDoList.addToList(event);
                } else {
                    throw new TrackBotException("Sorry, I did not understand that command.");
                }
            } catch (TrackBotException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        // close scanner and exit with message
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }
}
