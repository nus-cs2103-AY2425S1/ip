import java.util.Scanner;

public class Denim {

    static final String TASK_MARK = "mark";
    static final String TASK_UNMARK = "unmark";
    static final String TASK_LIST = "list";
    static final String TASK_TODO = "todo";
    static final String TASK_DEADLINE = "deadline";
    static final String TASK_EVENT = "event";
    static final String TASK_DELETE = "delete";

    static final String horizontalLine = "____________________________________________________________";
    static final String chatBotName = "Denim";
    static Task[] taskList = new Task[100];
    static int taskSize = 0;

    public static void main(String[] args) {
        displayGreetingMessage();
        // Scans User Input in the CLI
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            processInput(input);
            input = sc.nextLine();
        }
        displayExitMessage();
        sc.close();
    }

    static void displayGreetingMessage() {
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);
        System.out.println(greetingMessage);
    }

    static void displayExitMessage() {
        String byeMessage = String.format("%s%n %s%n%s", horizontalLine, "Bye. Hope to see you again soon!",
                horizontalLine);
        System.out.println(byeMessage);
    }

    static void processInput(String input) {
        String[] inputComponents = input.split(" ", 2);
        String command = inputComponents[0];
        String argument = inputComponents.length > 1 ? inputComponents[1] : "";

        try {
            switch (command) {
            case TASK_LIST:
                handleList();
                break;
            case TASK_MARK:
                handleMark(argument);
                break;
            case TASK_UNMARK:
                handleUnmark(argument);
                break;
            case TASK_DELETE:
                handleDelete(argument);
                break;
            case TASK_TODO:
                handleTodo(argument);
                break;
            case TASK_DEADLINE:
                handleDeadline(argument);
                break;
            case TASK_EVENT:
                handleEvent(argument);
                break;
            default:
                handleDefault(input);
                break;
            }
        } catch (DenimException e) {
            String errorMessage = String.format("%s%n I don't Understand what you mean T.T!\n Error: %s%n%s",
                    horizontalLine, e.getMessage(), horizontalLine);
            System.out.println(errorMessage);
        }
    }

    static void handleList() {
        System.out.println(horizontalLine);
        for (int i = 0; i < taskSize; i++) {
            System.out.printf("%d. %s\n", i + 1, taskList[i].toString());
        }
        System.out.println(horizontalLine);
    }

    static void handleTaskAddition(Task task) {
        taskList[taskSize++] = task;
        System.out.printf("%s%nGot it. I've added this task:%n   %s%nNow you have %d tasks in the list.%n%s%n",
                horizontalLine, task, taskSize, horizontalLine);
    }

    static void handleMark(String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to mark! ಠ▃ಠ");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskSize) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            taskList[index].setDone(true);
            System.out.printf("Okay, I've marked this task as done: \n %s\n", taskList[index]);
        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    static void handleUnmark(String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to unmark! (◡︿◡✿)");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskSize) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            taskList[index].setDone(false);
            System.out.printf("Okay, I've marked this task as not done yet: \n %s\n", taskList[index]);
        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    static void handleDelete(String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("I do not know what you are trying to delete! >.<");
        }

        try {
            int index = Integer.parseInt(argument) - 1;

            if (index < 0 || index >= taskSize) {
                throw new DenimException("OOPS!!! The task number is out of range. >=C");
            }

            Task deletedTask = taskList[index];

            // Shift elements after deletion
            for (int i = index; i < taskSize - 1; i++) {
                taskList[i] = taskList[i + 1];
            }

            // Nullify the last element and reduce the task size
            taskList[taskSize - 1] = null;
            taskSize--;

            String deleteMessage = String.format("%s%nGot it. I've deleted this task:%n   " +
                    "%s%nNow you have %d tasks in the list.%n%s%n", horizontalLine,
                    deletedTask, taskSize, horizontalLine);
            System.out.println(deleteMessage);
        } catch (NumberFormatException e) {
            throw new DenimException("y u do dis! Argument must be a valid numb3r! ( =ω=)..nyaa");
        }
    }

    static void handleTodo(String argument) throws DenimException {
        if (argument.isEmpty()) {
            throw new DenimException("The description of a todo cannot be empty! >.<");
        }
        Task toDoTask = new Todo(argument);
        handleTaskAddition(toDoTask);
    }

    static void handleDeadline(String argument) throws DenimException {
        String[] components = argument.split(" /by ");

        if (argument.isEmpty()) {
            throw new DenimException("The description of a deadline cannot be empty! >.<");
        }

        if (components.length < 2) {
            throw new DenimException("(,,◕　⋏　◕,,) Deadlines must include a 'by' slot!\n"
                    + " Example Usage: deadline homework /by 6pm wednesday");
        }

        Task deadlineTask = new Deadline(components[0], components[1]);
        handleTaskAddition(deadlineTask);
    }

    static void handleEvent(String argument) throws DenimException {
        String[] components = argument.split(" /from | /to ");

        if (argument.isEmpty()) {
            throw new DenimException("The description of an event cannot be empty! >.<");
        }

        if (components.length < 3) {
            throw new DenimException("!I'm Tired X.X T.T! The event must include both '/from' and '/to' time slots.\n"
            + " Example Usage: event dinner /from 6pm /to 8pm");
        }

        Task eventTask = new Event(components[0], components[1], components[2]);
        handleTaskAddition(eventTask);
    }

    static void handleDefault(String input) throws DenimException {
        throw new DenimException("Invalid Command!");
    }
}


