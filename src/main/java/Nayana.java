import java.util.Scanner;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It prints an ASCII logo and several lines of text to the console.
 */
public class Nayana {

    /**
     * The main method is the entry point of the application.
     * It prints a logo and a series of messages to the console.
     * and continues to process user input until the user types "bye"
     * Commands include listing tasks or adding new tasks.
     * The tasks can be marked as done or not done
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo =
              "                                                            \n" +
                    " ___ .-.     .---.   ___  ___    .---.   ___ .-.     .---.  \n" +
                    "(   )   \\   / .-, \\ (   )(   )  / .-, \\ (   )   \\   / .-, \\ \n" +
                    " |  .-. .  (__) ; |  | |  | |  (__) ; |  |  .-. .  (__) ; | \n" +
                    " | |  | |    .'`  |  | |  | |    .'`  |  | |  | |    .'`  | \n" +
                    " | |  | |   / .'| |  | '  | |   / .'| |  | |  | |   / .'| | \n" +
                    " | |  | |  | /  | |  '  `-' |  | /  | |  | |  | |  | /  | | \n" +
                    " | |  | |  ; |  ; |   `.__. |  ; |  ; |  | |  | |  ; |  ; | \n" +
                    " | |  | |  ' `-'  |   ___ | |  ' `-'  |  | |  | |  ' `-'  | \n" +
                    "(___)(___) `.__.'_.  (   )' |  `.__.'_. (___)(___) `.__.'_. \n" +
                    "                      ; `-' '                               \n" +
                    "                       .__.'                                ";

        System.out.println("Hello from" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nayana");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                command = checkCommand(command, taskList.getSize(),scanner);
            }catch(NayanaException e){
                command = "-1";
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
            if (command.equals("bye")) {
                System.out.println("Bye!!! Hope to help you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")){
                System.out.println(taskList);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.markAsDone(index);

            } else if (command.startsWith("unmark")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.markAsNotDone(index);


            } else if (command.startsWith("delete")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.delete(index);

            }else if (command.startsWith("deadline")){
                String[] parts = command.split(" /by ");
                String description = parts[0].substring(8).trim();
                String deadline = parts[1].trim();

                Deadlines nextTask = new Deadlines(description,deadline);
                taskList.addTask(nextTask);


            } else if (command.startsWith("event")) {
                String[] fromParts = command.split(" /from ");
                String description = fromParts[0].substring(5).trim();
                String[] toParts = fromParts[1].split(" /to ");
                String startTime = toParts[0].trim();
                String endTime = toParts[1].trim();

                Event nextTask = new Event(description,startTime,endTime);
                taskList.addTask(nextTask);



            } else if (command.startsWith("todo")) {
                String[] parts = command.split("todo ");
                String description = parts[1].trim();
                ToDos nextTask = new ToDos(description);
                taskList.addTask(nextTask);
            } else if (command.equals("-1")) {

            }

        }
        scanner.close();
    }

    private static String checkCommand(String command, int size, Scanner scanner) throws NayanaException{
        if (command.isBlank()) {
            throw new NayanaException("tasks cannot be empty :(");
        } else if (command.equals("bye") || command.equals("list")){
            return command;
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index < 0 || index >= size) {
                throw new NayanaException("index for mark/unmark is out of range :(");
            }
            return command;
        } else if (command.startsWith("deadline")) {
            String[] parts = command.split(" /by ");
            if (parts.length != 2) {
                throw new NayanaException("invalid format for deadline");
            } else if (parts[0].substring(8).trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new NayanaException("description and deadline cannot be empty.");
            }
            return command;

        } else if (command.startsWith("event")) {
            String[] fromParts = command.split(" /from ");
            if (fromParts.length != 2) {
                throw new NayanaException("invalid format for event");
            } else {
                String[] toParts = fromParts[1].split(" /to ");
                if (toParts.length != 2) {
                    throw new NayanaException("invalid format for event");
                } else if (fromParts[0].substring(5).trim().isEmpty() ||
                      toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
                    throw new NayanaException("description, start time and end time cannot be empty.");
                }
            }
            return command;
        } else if (command.startsWith("todo")) {
            String[] parts = command.split("todo ");
            if (parts.length != 2) {
                System.out.println("1");
                throw new NayanaException("invalid format for todo");
            } else if (parts[1].trim().isEmpty()) {
                throw new NayanaException("description cannot be empty.");
            }
            return command;
        } else {
            throw new NayanaException("Please use the correct format and start your prompts \nwith " +
                  "deadline, event, todo, mark, unmark, delete, list, bye ");
        }

    }

}
