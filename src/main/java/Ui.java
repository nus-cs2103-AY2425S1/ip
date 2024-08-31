import java.util.Objects;

public class Ui {
    private static final String HORIZONTAL = "____________________________________________________________";
    private static final String GREETING = " heyyy im edith!\n what can I do for you?";
    private static final String FAREWELL = " bye!! see you soon love <3";
    private static final String LINEBREAK = "\n";

    public static void handleUserInput(String userInput, ToDoList toDoList) {
        String command = Parser.getCommand(userInput);

        if (Objects.equals(command, "list")) { // check if user wants the todo list
            System.out.println(" tasks in your todo list!" + LINEBREAK +
                    toDoList.toString());
        }

        else if (Objects.equals(command, "mark") || Objects.equals(command, "unmark")) { // check if user wants to mark a task
            changeTaskStatus(userInput, toDoList);
        }

        else if (Objects.equals(command, "todo") || Objects.equals(command, "deadline") ||
                Objects.equals(command, "event")) {
            addTask(userInput, toDoList);
        }

        else if (Objects.equals(command, "delete")) {
            delete(userInput, toDoList);
        }

        else {
            try {
                otherCommand();
            } catch (InvalidCommandException e) {
                showError(e.getMessage());
            }
        }
    }

    public static void greetUser() {
        System.out.println(HORIZONTAL + LINEBREAK + GREETING + LINEBREAK + HORIZONTAL);
    }

    public static void bidFarewell() {
        System.out.println(FAREWELL + LINEBREAK);
    }

    public static void changeTaskStatus(String userInput, ToDoList toDoList) {
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            String command = Parser.getCommand(userInput);

            if (Objects.equals(command, "mark")) { // check if user wants to mark a task
                toDoList.mark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(" " + "yay! i've marked this task as done #productive:" + LINEBREAK +
                        "   " + toDoList.getTask(taskNumber) + LINEBREAK);
            } else { // unmarking a task
                toDoList.unmark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(" " + "aw, i've marked this task as undone:" + LINEBREAK +
                        "   " + toDoList.getTask(taskNumber) + LINEBREAK);
            }

        } catch (MissingTaskNumberException | InvalidTaskNumberException e) { // if there is a missing task number
            showError(e.getMessage());
        }
    }

    public static void addTask(String userInput, ToDoList toDoList) {
        String taskType = Parser.getCommand(userInput);
        try {
            String taskDetails = Parser.getTaskDetails(userInput, taskType);

            if (Objects.equals(taskType, "todo")) {
                addToDoTask(taskDetails, toDoList);
            }

            if (Objects.equals(taskType, "deadline")) {
                addDeadlineTask(taskDetails, taskType, toDoList);
            }

            if (Objects.equals(taskType, "event")) {
                addEventTask(taskDetails, taskType, toDoList);
            }
        } catch (MissingTaskNameException e) {
            showError(e.getMessage());
        }
    }

    public static void addToDoTask(String taskName, ToDoList toDoList) {
        ToDoTask task = new ToDoTask(taskName);
        toDoList.add(task);
        System.out.println(" " + "nice! i've added this task:" + LINEBREAK +
                " " + task + LINEBREAK +
                " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + LINEBREAK);
    }

    public static void addDeadlineTask(String taskDetails, String taskType, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, taskType);
            String taskDeadline = Parser.getTaskDeadline(taskDetails);
            DeadlineTask task = new DeadlineTask(taskName, taskDeadline);
            toDoList.add(task);
            System.out.println(" " + "nice! i've added this task:" + LINEBREAK +
                    " " + task + LINEBREAK +
                    " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + LINEBREAK);
        } catch (MissingDeadlineException e) {
            showError(e.getMessage());
        }
    }

    public static void addEventTask(String taskDetails, String taskType, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, taskType);
            String taskDuration = Parser.getTaskDuration(taskDetails);
            String taskStart = Parser.getTaskStart(taskDuration);
            String taskEnd = Parser.getTaskEnd(taskDuration);
            EventTask task = new EventTask(taskName, taskStart, taskEnd);
            toDoList.add(task);
            System.out.println(" " + "nice! i've added this task:" + LINEBREAK +
                    " " + task + LINEBREAK +
                    " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + LINEBREAK);
        } catch (MissingEventDurationException e) {
            showError(e.getMessage());
        }
    }

    public static void otherCommand() throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    public static void delete(String userInput, ToDoList toDoList) {
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            System.out.println(" okay! i've deleted this task:" + LINEBREAK +
                    "   " + toDoList.getTask(taskNumber) + LINEBREAK +
                    " you currently have " + (toDoList.getNumberofTasks() - 1) + " tasks in your todo list"
                    + LINEBREAK);
            toDoList.delete(taskNumber);
        } catch (MissingTaskNumberException | InvalidTaskNumberException e) {
            showError(e.getMessage());
        }
    }

    public static void showLine() {
        System.out.println(HORIZONTAL + LINEBREAK);
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
