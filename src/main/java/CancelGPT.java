import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;
    private final List<Task> TASKS_LIST;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new ArrayList<>();
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
        while (!command.equals("bye")) {
            System.out.println(horizontalLine);
            handleCommand(command);
            System.out.println(horizontalLine);
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
            if (command.equals("list")) {
                displayTasksList();
            } else if (command.startsWith("delete")) {
                int taskNumber = parseDeleteTaskCommand(command);
                deleteTask(taskNumber);
            } else if (command.startsWith("mark")) {
                int taskNumber = parseMarkTaskCommand(command);
                markTask(taskNumber);
            } else if (command.startsWith("unmark")) {
                int taskNumber = parseUnmarkTaskCommand(command);
                unmarkTask(taskNumber);
            } else if (command.startsWith("todo")) {
                Task toDoTask = parseToDoTaskCreationCommand(command);
                handleAddingTask(toDoTask);
            } else if (command.startsWith("deadline")) {
                Task deadlineTask = parseDeadlineTaskCreationCommand(command);
                handleAddingTask(deadlineTask);
            } else if (command.startsWith("event")) {
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
        if (taskNumber <= 0 || taskNumber > TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        Task taskDeleted = this.TASKS_LIST.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + taskDeleted);
        System.out.println("Now you have " + this.TASKS_LIST.size() + " tasks in the list.");
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
        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, commandArray.length);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Deadline task");
        }

        int byIndex = Arrays.asList(commandArray).indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidTask("Missing `by` for Deadline task");
        }
        String[] byDateArr = Arrays.copyOfRange(commandArray, byIndex + 1, commandArray.length);
        String byDate = String.join(" ", byDateArr);
        if (byDate.isEmpty()) {
            throw new InvalidTask("Missing by date for Deadline task");
        }
        return new Deadline(taskDescription, byDate);
    }

    public Task parseEventTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");
        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, commandArray.length);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Event task");
        }

        int fromIndex = Arrays.asList(commandArray).indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidTask("Missing `from` for Event task");
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

        return new Event(taskDescription, fromDate, toDate);
    }

    public void handleAddingTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + addToTaskList(task));
        System.out.println("Now you have " + this.TASKS_LIST.size() + " tasks in the list.");
    }

    public void markTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public String addToTaskList(Task task) {
        this.TASKS_LIST.add(task);
        return task.toString();
    }

    public void displayTasksList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.TASKS_LIST.size(); i++) {
            System.out.println(i + 1 + ". " + this.TASKS_LIST.get(i));
        }
    }
}
