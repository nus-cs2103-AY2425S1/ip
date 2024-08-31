import java.util.Objects;

public class Ui {
    private static final String HORIZONTAL = "____________________________________________________________";
    private static final String GREETING = " heyyy im edith!\n what can I do for you?";
    private static final String FAREWELL = " bye!! see you soon love <3";
    private static final String LINEBREAK = "\n";

    public static void handleUserInput(String userInput, ToDoList toDoList) {
        String command = Parser.getCommand(userInput);

        if (Objects.equals(command, "list")) { // check if user wants the todo list
            System.out.println(HORIZONTAL + LINEBREAK +
                    " tasks in your todo list!" + LINEBREAK +
                    toDoList.toString() +
                    HORIZONTAL);
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
                System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
            }
        }
    }

    public static void greetUser() {
        System.out.println(HORIZONTAL + LINEBREAK + GREETING + LINEBREAK + HORIZONTAL);
    }

    public static void bidFarewellToUser() {
        System.out.println(HORIZONTAL + LINEBREAK + FAREWELL + LINEBREAK + HORIZONTAL);
    }

    public static void changeTaskStatus(String userInput, ToDoList toDoList) {
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            String command = Parser.getCommand(userInput);

            if (Objects.equals(command, "mark")) { // check if user wants to mark a task
                toDoList.mark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(HORIZONTAL + LINEBREAK +
                        " " + "yay! i've marked this task as done #productive:" + LINEBREAK +
                        "   " + toDoList.getTask(taskNumber) + LINEBREAK +
                        HORIZONTAL);
            } else { // unmarking a task
                toDoList.unmark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(HORIZONTAL + LINEBREAK +
                        " " + "aw, i've marked this task as undone:" + LINEBREAK +
                        "   " + toDoList.getTask(taskNumber) + LINEBREAK +
                        HORIZONTAL);
            }

        } catch (MissingTaskNumberException | InvalidTaskNumberException e) { // if there is a missing task number
            System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
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
            System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
        }
    }

    public static void addToDoTask(String taskName, ToDoList toDoList) {
        ToDoTask task = new ToDoTask(taskName);
        toDoList.add(task);
        System.out.println(HORIZONTAL + LINEBREAK +
                " " + "nice! i've added this task:" + LINEBREAK +
                " " + task + LINEBREAK +
                " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + LINEBREAK +
                HORIZONTAL);
    }

    public static void addDeadlineTask(String taskDetails, String taskType, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, taskType);
            String taskDeadline = Parser.getTaskDeadline(taskDetails);
            DeadlineTask task = new DeadlineTask(taskName, taskDeadline);
            toDoList.add(task);
            System.out.println(HORIZONTAL + LINEBREAK +
                    " " + "nice! i've added this task:" + LINEBREAK +
                    " " + task + LINEBREAK +
                    " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list."
                    + LINEBREAK + HORIZONTAL);
        } catch (MissingDeadlineException e) {
            System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
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
            System.out.println(HORIZONTAL + LINEBREAK +
                    " " + "nice! i've added this task:" + LINEBREAK +
                    " " + task + LINEBREAK +
                    " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + LINEBREAK +
                    HORIZONTAL);
        } catch (MissingEventDurationException e) {
            System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
        }
    }

    public static void otherCommand() throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    public static void delete(String userInput, ToDoList toDoList) {
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            System.out.println(HORIZONTAL + LINEBREAK +
                    " okay! i've deleted this task:" + LINEBREAK +
                    "   " + toDoList.getTask(taskNumber) + LINEBREAK +
                    " you currently have " + (toDoList.getNumberofTasks() - 1) + " tasks in your todo list"
                    + LINEBREAK + HORIZONTAL);
            toDoList.delete(taskNumber);
        } catch (MissingTaskNumberException | InvalidTaskNumberException e) {
            System.out.println(HORIZONTAL + LINEBREAK + e.getMessage() + LINEBREAK + HORIZONTAL);
        }
    }
}
