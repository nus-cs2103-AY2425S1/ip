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
            try {
                if (userInput.equals("list")) {
                    Reply.printMessage(taskList.printList());
                } else if (userInput.equals("bye")) {
                    break;
                } else if (userInput.startsWith("mark")) {
                    handleMarkTask(userInput, true);
                } else if (userInput.startsWith("unmark")) {
                    handleMarkTask(userInput, false);
                } else if (userInput.startsWith("todo")) {
                    handleAddTodo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    handleAddDeadline(userInput);
                } else if (userInput.startsWith("event")) {
                    handleAddEvent(userInput);
                } else if (userInput.startsWith("delete")) {
                    handleDeleteTask(userInput);
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                Reply.printMessage(e.toString());
            } catch (MissingTaskNameException e) {
                Reply.printMessage(e.toString());
            } catch (MissingDateException e) {
                Reply.printMessage(e.toString());
            } catch (TaskNotFoundException e) {
                Reply.printMessage(e.toString());
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
    public static void handleMarkTask(String message, boolean mark) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
           throw new InvalidInputException();
        }
        try {
            if (mark) {
                taskList.markTask(Integer.parseInt(split[1]));
            } else {
                taskList.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e ) {
            Reply.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            Reply.printMessage("Index number does not exist");
        }
    }

    /**
     * add todo
     *
     * @param message input of user
     */
    public static void handleAddTodo(String message) throws MissingTaskNameException {
        String taskName = message.replace("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("todo");
        }
        taskList.addTask(new Todo(taskName));
    }

    /**
     * add deadline
     *
     * @param message input of user
     */
    public static void handleAddDeadline(String message) throws MissingDateException, MissingTaskNameException {

        String[] parts = message.split(" /by ");
        String taskName = parts[0].replace("deadline", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("deadline");
        }
        if (parts.length != 2) {
            throw new MissingDateException("deadline");
        }
        String by = parts[1].trim();
        taskList.addTask(new Deadline(taskName, by));
    }

    /**
     * add event
     *
     * @param message input of user
     */
    public static void handleAddEvent(String message) throws MissingDateException, MissingTaskNameException{
        String[] parts = message.split(" /from | /to ");
        String taskName = parts[0].replace("event", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("deadline");
        }
        if (parts.length != 3) {
            throw new MissingDateException("event");
        }
        String from = parts[1].trim();
        String to = parts[2].trim();
        taskList.addTask(new Event(taskName, from, to));
    }

    public static void handleDeleteTask(String message) throws InvalidInputException, TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            taskList.deleteTask(Integer.parseInt(split[1]));
        } catch (NumberFormatException e ) {
            Reply.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            Reply.printMessage("Index number does not exist");
        }
    }
}

