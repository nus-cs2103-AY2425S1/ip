import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Echo {
    private static final String LINE_BREAK = "-------------------------------------";

    /** List of valid commands for Echo */
    private final List<String> validCommands = Arrays.asList(
            "list",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "bye",
            "delete"
    );

    /**
     * Prints a greeting when users first run the program
     */
    public void greet() {
        System.out.println(LINE_BREAK + "\nHello: I'm Echo\nWhat can I do for you?\n" + LINE_BREAK);
    }

    /**
     * Prints a goodbye message when users end the program
     */
    public void bye() {
        System.out.println(LINE_BREAK + "\nBye. Hope to see you again soon!\n" + LINE_BREAK);
    }

    /**
     * Returns the index provided by user in the description as an integer
     *
     * @param taskDescription index given by user as a string
     * @return the index as an integer
     * @throws EchoException if taskDescription is empty
     */
    public int getIndexFromInput(String taskDescription) throws EchoException{
        if (taskDescription.isEmpty()) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a " +
                    "description of what to do.\n" + LINE_BREAK);
        }
        return Integer.parseInt(taskDescription) - 1;
    }

    /**
     * Creates a ToDos object with the given task description
     *
     * @param taskDescription description of the ToDos task
     * @return ToDos object
     * @throws EchoException if taskDescription is empty
     */
    public ToDos createTodoTask(String taskDescription) throws EchoException{
        if (taskDescription.isEmpty()) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a " +
                    "description of what to do.\n" + LINE_BREAK);
        }
        return new ToDos(taskDescription);
    }

    /**
     * Creates a Deadlines object with the given task description
     *
     * @param taskDescription description of the Deadlines task and the deadline date
     * @return Deadlines object
     * @throws EchoException if deadline date is not provided by the user and taskDescription is empty
     */
    public Deadlines createDeadlineTask(String taskDescription) throws EchoException {
        if (taskDescription.isEmpty()) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a " +
                    "description of what to do.\n" + LINE_BREAK);
        }

        String[] deadlineArray = taskDescription.split(" /by ");
        if (deadlineArray.length == 1) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a " +
                    "deadline for the task.\n" + LINE_BREAK);
        }
        String deadlineDescription = deadlineArray[0];
        String deadlineDate = deadlineArray[1];
        return new Deadlines(deadlineDescription, deadlineDate);
    }

    /**
     * Creates an Events object with the given task description
     *
     * @param taskDescription description of the Events task, start time and end time
     * @return an Events object
     * @throws EchoException if the start time or the end time is not provided by the user and
     * taskDescription is empty
     */
    public Events createEventTask(String taskDescription) throws EchoException {
        if (taskDescription.isEmpty()) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a " +
                    "description of what to do.\n" + LINE_BREAK);
        }

        String[] eventArray = taskDescription.split(" /from | /to ");
        if (eventArray.length < 3) {
            throw new EchoException(LINE_BREAK + "\nSorry! Please include a start and " +
                    "end time for the event.\n" + LINE_BREAK);
        }
        String eventDescription = eventArray[0];
        String eventStartTime = eventArray[1];
        String eventEndTime = eventArray[2];
        return new Events(eventDescription, eventStartTime, eventEndTime);
    }

    /**
     * Prints the relevant display message after users enter an input and
     * executes the commands in the input
     *
     * @param input command and description entered by users
     * @param taskList the arrayList to store all the Task object created when user uses the program
     * @throws EchoException if the command in the input is not valid,
     * if there are insufficient details to create a Task object or perform the command
     */
    public void checkInput(String input, TaskList taskList) throws EchoException {
        try {
            String[] replyArray = input.split(" ", 2);
            String command = replyArray[0];

            //Throw error if it does not contain the valid commands
            if (!validCommands.contains(command)) {
                throw new EchoException(LINE_BREAK + "\nSorry! I don't get what you mean\n" + LINE_BREAK);
            }

            String taskDescription = (replyArray.length > 1) ? replyArray[1] : "";

            switch (command) {
            case "list":
                taskList.listAllTask();
                break;
            case "mark":
                int index = getIndexFromInput(taskDescription);
                Task currTask1 = taskList.getTask(index);
                currTask1.mark();
                break;
            case "unmark":
                int index1 = getIndexFromInput(taskDescription);
                Task currTask2 = taskList.getTask(index1);
                currTask2.unmark();
                break;
            case "todo":
                ToDos toDoTask = this.createTodoTask(taskDescription);
                taskList.addTask(toDoTask);
                break;
            case "deadline":
                Deadlines deadlineTask = this.createDeadlineTask(taskDescription);
                taskList.addTask(deadlineTask);
                break;
            case "event":
                Events eventTask = createEventTask(taskDescription);
                taskList.addTask(eventTask);
                break;
            case "delete":
                int index2 = getIndexFromInput(taskDescription);
                taskList.deleteTask(index2);
                break;
            default:
                System.out.println(input);
                break;
            }
        } catch (EchoException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Echo echo = new Echo();
        echo.greet();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo.checkInput(input, taskList);
            input = scanner.nextLine();
        }
        scanner.close();
        echo.bye();
    }
}
