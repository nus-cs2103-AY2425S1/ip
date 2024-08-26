package joe;
import java.util.ArrayList;

public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n";
    public static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.\n";

    private final String CHATBOT_NAME;
    public Ui(String chatbotName) {
        this.CHATBOT_NAME = chatbotName;
    }

    public void greet() {
        System.out.printf("%s\nHello! I'm %s\nWhat can I do for you?\n%s\n", HORIZONTAL_LINE, CHATBOT_NAME, HORIZONTAL_LINE);
    }

    public void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", HORIZONTAL_LINE);
    }

    public void printHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("list - List all tasks");
        System.out.println("todo <task> - Add a todo task");
        System.out.println("deadline <task> /by <date> - Add a deadline task with date in yyyy-mm-dd format");
        System.out.println("event <task> /from <date> /to <date> - Add an event task with dates in yyyy-mm-dd format");
        System.out.println("mark <index> - Mark a task as done");
        System.out.println("unmark <index> - Mark a task as not done yet");
        System.out.println("delete <index> - Delete a task");
        System.out.println("help - Show this help message");
        System.out.println("bye - Exit the program");
    }

    public void printListMessage(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDoneMessage(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printUndoneMessage(Task task) {
        System.out.printf("Nice! I've marked this task as not done yet:\n%s\n", task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDeleteMessage(Task task, int size) {
        System.out.printf("Noted. I've removed this task:\n%s\n", task);
        System.out.printf(TASK_COUNT_MESSAGE, size);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printTodoMessage(Task task, int size) {
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, task);
        System.out.printf(TASK_COUNT_MESSAGE, size);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDeadlineMessage(Task task, int size) {
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, task);
        System.out.printf(TASK_COUNT_MESSAGE, size);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printEventMessage(Task task, int size) {
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, task);
        System.out.printf(TASK_COUNT_MESSAGE, size);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printEmptyTaskErrorMessage() {
        System.out.println("Don't expect me to remember nothing!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printEmptyByErrorMessage() {
        System.out.println("BY WHEN??!!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printInvalidEventDateErrorMessage() {
        System.out.println("Give me a valid from and to!");
        System.out.println(HORIZONTAL_LINE);
    }
}
