import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Reply.printGreeting();

        handleUserInput();

        Reply.printGoodbye();

    }

    public static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("list")){
                Reply.printMessage(taskList.printTodo());
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.startsWith("mark")) {
                handleMarkTask(userInput, true);
            } else if (userInput.startsWith("unmark")){
                handleMarkTask(userInput, false);
            } else if (userInput.startsWith("todo")){
                handleAddTodo(userInput);
            } else if (userInput.startsWith("deadline")){
                handleAddDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                handleAddEvent(userInput);
            } else {
                Reply.printMessage(" Invalid Input");
            }
        }

        scanner.close();
    }

    /**
     * mark or mark specific task
     *
     * @param message input of user
     * @param mark boolean to mark or unmark task
     */
    public static void handleMarkTask(String message, boolean mark) {
        String[] split = message.split(" ");
        if (split.length > 2) {
            Reply.printMessage(" Invalid input");
            return;
        }

        try {
            if (mark) {
                taskList.markTask(Integer.parseInt(split[1]));
            } else {
                taskList.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e ) {
            Reply.printMessage(" Invalid number");
        } catch (IndexOutOfBoundsException e) {
            Reply.printMessage(" Index number does not exist");
        }
    }

    /**
     * add todo
     *
     * @param message input of user
     */
    public static void handleAddTodo(String message) {
        String taskName = message.replace("todo ", "").trim();
        taskList.addTask(new Todo(taskName));
    }

    /**
     * add deadline
     *
     * @param message input of user
     */
    public static void handleAddDeadline(String message) {
        String[] parts = message.split(" /by ");
        if (parts.length == 2) {
            String taskName = parts[0].replace("deadline ", "").trim();
            String by = parts[1].trim();
            taskList.addTask(new Deadline(taskName, by));
        } else {
            Reply.printMessage(" Incorrect format for deadline task. Please use the format: " +
                    "'deadline <task> /by <date>'.");
        }
    }

    /**
     * add event
     *
     * @param message input of user
     */
    public static void handleAddEvent(String message) {
        String[] parts = message.split(" /from | /to ");
        if (parts.length == 3) {
            String taskName = parts[0].replace("event ", "").trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            taskList.addTask(new Event(taskName, from, to));
        } else {
            Reply.printMessage(" Incorrect format for event task. Please use the format: 'event <task> " +
                    "/from <start time> /to <end time>'.");
        }
    }
}

