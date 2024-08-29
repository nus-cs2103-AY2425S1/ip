import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;
    private final TasksList TASKS_LIST;
    private TasksStorage tasksStorage;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new TasksList();
        try {
            this.tasksStorage = new TasksStorage(this);
        } catch (IOException e) {
            System.out.println("Unable to use TASKS STORAGE. Exiting program");
            System.exit(1);
        }
    }

    public String getName() {
        return this.CHATBOT_NAME;
    }

    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT();
        cancelGPT.run();
    }

    public void run() {
        String horizontalLine = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine);
        greet();
        System.out.println(horizontalLine);

        String command = sc.nextLine();
        while (!command.equals(Command.BYE.toString())) {
            System.out.println(horizontalLine);
            handleCommand(command);
            System.out.println(horizontalLine);

            try {
                saveTasks();
            } catch (IOException e) {
                System.out.println("Unable to save tasks to TASKS STORAGE. Exiting program");
                System.exit(1);
            }

            command = sc.nextLine();
        }

        sc.close();
        System.out.println(horizontalLine);
        exit();
        System.out.println(horizontalLine);
    }

    public void greet() {
        System.out.println("Hello! I am " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Good bye. Hope to see you again soon!");
    }

    public void handleCommand(String command) {
        try {
            if (command.equals(Command.LIST.toString())) {
                displayTasksList();
            } else if (command.startsWith(Command.DELETE.toString())) {
                int taskNumber = parseDeleteTaskCommand(command);
                deleteTask(taskNumber);
            } else if (command.startsWith(Command.MARK.toString())) {
                int taskNumber = parseMarkTaskCommand(command);
                markTask(taskNumber);
            } else if (command.startsWith(Command.UNMARK.toString())) {
                int taskNumber = parseUnmarkTaskCommand(command);
                unmarkTask(taskNumber);
            } else if (command.startsWith(Command.TODO.toString())) {
                Task toDoTask = parseToDoTaskCreationCommand(command);
                handleAddingTask(toDoTask);
            } else if (command.startsWith(Command.DEADLINE.toString())) {
                Task deadlineTask = parseDeadlineTaskCreationCommand(command);
                handleAddingTask(deadlineTask);
            } else if (command.startsWith(Command.EVENT.toString())) {
                Task eventTask = parseEventTaskCreationCommand(command);
                handleAddingTask(eventTask);
            } else {
                throw new UnknownInput();
            }
        } catch (MarkTaskInputException | UnmarkTaskInputException | InvalidTask | TaskDoesNotExist | UnknownInput | DeleteTaskInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.deleteTask(taskNumber);
    }

    public int parseDeleteTaskCommand(String command) throws DeleteTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new DeleteTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new DeleteTaskInputException();
        }
    }
    public int parseMarkTaskCommand(String command) throws MarkTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new MarkTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new MarkTaskInputException();
        }
    }

    public int parseUnmarkTaskCommand(String command) throws UnmarkTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new UnmarkTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new UnmarkTaskInputException();
        }
    }

    public Task parseToDoTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");
        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, commandArray.length);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for ToDo task");
        }
        return new ToDo(taskDescription);
    }

    public Task parseDeadlineTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");

        int byIndex = Arrays.asList(commandArray).indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidTask("Missing `by` for Deadline task");
        }

        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, byIndex);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Deadline task");
        }

        String[] byDateArr = Arrays.copyOfRange(commandArray, byIndex + 1, commandArray.length);
        String byDate = String.join(" ", byDateArr);
        if (byDate.isEmpty()) {
            throw new InvalidTask("Missing by date for Deadline task");
        }

        Task deadlineTask;

        try {
            deadlineTask = new Deadline(taskDescription, LocalDateTimeHandler.parseLocalDateTime(byDate));
        } catch (DateTimeParseException e) {
            throw new InvalidTask("Invalid deadline date");
        }
        return deadlineTask;
    }

    public Task parseEventTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");

        int fromIndex = Arrays.asList(commandArray).indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidTask("Missing `from` for Event task");
        }

        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, fromIndex);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Event task");
        }


        int toIndex = Arrays.asList(commandArray).indexOf("/to");
        if (toIndex == -1) {
            throw new InvalidTask("Missing `to` for Event task");
        }

        String[] fromDateArr = Arrays.copyOfRange(commandArray, fromIndex + 1, toIndex);
        String fromDate = String.join(" ", fromDateArr);
        if (fromDate.isEmpty()) {
            throw new InvalidTask("Missing from date for Event task");
        }

        String[] toDateArr = Arrays.copyOfRange(commandArray, toIndex + 1, commandArray.length);
        String toDate = String.join(" ", toDateArr);
        if (toDate.isEmpty()) {
            throw new InvalidTask("Missing to date for Event task");
        }

        Task eventTask;
        try {
            eventTask = new Event(taskDescription, LocalDateTimeHandler.parseLocalDateTime(fromDate),
                    LocalDateTimeHandler.parseLocalDateTime(toDate));
        } catch (DateTimeParseException e) {
            throw new InvalidTask("Invalid event date(s)");
        }
        return eventTask;
    }

    public void handleAddingTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + this.addToTaskList(task));
        System.out.println("Now you have " + this.TASKS_LIST.getSize() + " tasks in the list.");
    }

    public void markTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.markTask(taskNumber);
    }

    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.unmarkTask(taskNumber);
    }

    public String addToTaskList(Task task) {
        return this.TASKS_LIST.addToTaskList(task);
    }

    public void displayTasksList() {
        this.TASKS_LIST.displayTasksList();
    }

    public void saveTasks() throws IOException {
        this.tasksStorage.saveTasks();
    }

    public List<Task> getTasks() {
        return this.TASKS_LIST.getTasksList();
    }
}
