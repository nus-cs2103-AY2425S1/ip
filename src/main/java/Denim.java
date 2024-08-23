import java.util.Scanner;

public class Denim {

    static final String TASK_MARK = "mark";
    static final String TASK_UNMARK = "unmark";
    static final String TASK_LIST = "list";
    static final String TASK_TODO = "todo";
    static final String TASK_DEADLINE = "deadline";
    static final String TASK_EVENT = "event";

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

    static void handleMark(String argument) {
        int index = Integer.parseInt(argument) - 1;
        taskList[index].setDone(true);
        System.out.printf("Okay, I've marked this task as done: \n %s\n", taskList[index]);
    }

    static void handleUnmark(String argument) {
        int index = Integer.parseInt(argument) - 1;
        taskList[index].setDone(false);
        System.out.printf("Okay, I've marked this task as not done yet: \n %s\n", taskList[index]);
    }

    static void handleTodo(String argument) {
        Task toDoTask = new Todo(argument);
        handleTaskAddition(toDoTask);
    }

    static void handleDeadline(String argument) {
        String[] components = argument.split(" /by ");
        Task deadlineTask = new Deadline(components[0], components[1]);
        handleTaskAddition(deadlineTask);
    }

    static void handleEvent(String argument) {
        String[] components = argument.split(" /from | /to ");
        Task eventTask = new Event(components[0], components[1], components[2]);
        handleTaskAddition(eventTask);
    }

    static void handleDefault(String input) {
        Task newTask = new Task(input);
        handleTaskAddition(newTask);
    }
}


