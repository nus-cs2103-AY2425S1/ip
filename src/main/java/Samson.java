import java.util.Scanner;
public class Samson {
    public static void main(String[] args) throws SamException {
        final String chatBoxName = "Samson";
        Greeting greeting = new Greeting(chatBoxName);
        TaskManager taskManager =  new TaskManager();

        greeting.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    greeting.exit();
                    break;
                }

                if (userInput.equalsIgnoreCase("list")) {
                    taskManager.listTask();
                } else if (userInput.startsWith("mark")) {
                    String[] chunk = userInput.split(" ");
                    // handle edge error where user does not input task number
                    if (chunk.length != 2) {
                        throw new SamException("Please provide valid Task Number");
                    }
                    int taskNum = Integer.parseInt(chunk[1]);
                    taskManager.markTask(taskNum);
                } else if (userInput.startsWith("unmark")) {
                    String[] chunk = userInput.split(" ");
                    if (chunk.length != 2) {
                        throw new SamException("Please provide valid Task Number");
                    }
                    int taskNum = Integer.parseInt(chunk[1]);
                    taskManager.unmarkTask(taskNum);
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new SamException("Please provide your description!!!!!!!!!");
                    }
                    String description = userInput.substring(5);
                    taskManager.addTask(new ToDo(description));
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split(" /by ");
                    if (parts.length != 2 || parts[0].substring(9).trim().isEmpty()) {
                        throw new SamException("Please provide the description and deadline of your task");
                    }
                    String description = parts[0].substring(9);
                    String by = parts[1];
                    taskManager.addTask(new Deadline(description, by));
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.split(" /from | /to ");
                    if (parts.length != 3 || parts[0].substring(6).trim().isEmpty()) {
                        throw new SamException("The description, start, and end time of an event cannot be empty.");
                    }
                    String description = parts[0].substring(6);
                    String from = parts[1];
                    String to = parts[2];
                    taskManager.addTask(new Event(description, from, to));
                } else if (userInput.startsWith("delete")){
                    String[] chunk = userInput.split(" ");
                    if (chunk.length != 2) {
                        throw new SamException("Please provide valid Task Number");
                    }
                    int taskNum = Integer.parseInt(chunk[1]);
                    taskManager.deleteTask(taskNum);
                } else {
                    throw new SamException("I'm sorry, but I don't know what that means :-( \n " +
                            "Kindly provide the tasks starting with 'todo', 'event' or 'deadline'!!");
                }
            } catch (SamException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();

    }
}
