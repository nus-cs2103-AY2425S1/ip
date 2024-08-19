import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Talkie {

    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        // Initialisation of Scanner
        Scanner scanner = new Scanner(System.in);

        String logo = """
                -----------------------------------------------\s
                Hello! I'm Talkie, your friendly ChatBot.\s
                What can I do for you?\s
                -----------------------------------------------\s
                """;

        String byeMessage = """ 
                -----------------------------------------------\s
                Bye. Hope to see you again soon!\s
                -----------------------------------------------\s
                """;

        System.out.println(logo);

        // Program runs until user inputs "bye"
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMessage);
                break;
            }

            if (input.equalsIgnoreCase("list")) {

                String listMessage = "";

                for (int i=0; i<taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    String description = (i + 1) + ". " + currTask + "\n";
                    listMessage += description;
                }

                String finalListMessage = "-----------------------------------------------\n"
                        + "Here are the tasks in your list:\n"
                        +  listMessage
                        + "-----------------------------------------------\n";
                System.out.println(finalListMessage);
                continue;
            }

            if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = taskList.get(index);
                    task.markAsDone();
                    String doneMessage = "Nice! I've marked this task as done:\n"
                            + task + "\n"
                            + "______________________________________________\n";
                    System.out.println(doneMessage);
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
                continue;
            }

            if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = taskList.get(index);
                    task.markAsNotDone();
                    String undoneMessage = "OK, I've marked this task as not done yet:\n"
                            + task + "\n"
                            + "______________________________________________\n";
                    System.out.println(undoneMessage);
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
                continue;
            }


            // Initialise a new Item Object
            Task t = new Task(input);
            taskList.add(t);

            String toDoMessage = "-----------------------------------------------\n"
                    + "Added: " + t.getDesc() + "\n"
                    + "-----------------------------------------------\n";

            System.out.println(toDoMessage);
        }

        // Close the scanner when the program ends
        scanner.close();
    }
}
