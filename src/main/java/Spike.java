import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

enum InputType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    TASK,
    ERROR
}

public class Spike {

    private static ArrayList<Task> toDoList = new ArrayList<>();

    public static void helloMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Spike\nWhat can I do for you?");
        System.out.println("_________________________________________________________");
        return;
    }

    public static void byeMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            String[] inputSplit = input.split(" ", 2);
            InputType inputType = parseInput(inputSplit[0]);

            switch (inputType) {
                case BYE:
                    byeMessage();
                    break;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(inputSplit[1]);
                    break;
                case UNMARK:
                    unmarkTask(inputSplit[1]);
                    break;
                case TODO:
                    addToDo(inputSplit[1]);
                    break;
                case DEADLINE:
                    addDeadline(inputSplit[1]);
                    break;
                case EVENT:
                    addEvent(inputSplit[1]);
                    break;
                case ERROR:
                    System.out.println("     _________________________________________________________");
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("     _________________________________________________________");
                    break;
            }

            if (inputType == InputType.BYE) {
                break;
            }
        }
        scanner.close();
    }

    private static InputType parseInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return InputType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return InputType.LIST;
        } else if (input.equalsIgnoreCase("mark")) {
            return InputType.MARK;
        } else if (input.equalsIgnoreCase("unmark")) {
            return InputType.UNMARK;
        } else if (input.equalsIgnoreCase("todo")) {
            return InputType.TODO;
        } else if (input.equalsIgnoreCase("deadline")) {
            return InputType.DEADLINE;
        } else if (input.equalsIgnoreCase("event")) {
            return InputType.EVENT;
        } else {
            return InputType.ERROR;
        }
    }

    private static void listTasks() {
        System.out.println("     _________________________________________________________");
        System.out.println("      Here are the tasks in your list:");
        int counter = 0;
        for (Task item : toDoList) {
            counter++;
            System.out.println("      " + counter + ". " + item.toString());
        }
        System.out.println("     _________________________________________________________");
    }

    public static void markTask(String input) {
        int index = Integer.parseInt(input) - 1;
        toDoList.get(index).markAsDone();
    }

    public static void unmarkTask(String input) {
        int index = Integer.parseInt(input) - 1;
        toDoList.get(index).unmark();
    }

    public static void addToDo(String input) {
        ToDo toDo = new ToDo(input);
        toDoList.add(toDo);
        addTaskString(toDo.toString());
    }

    public static void addEvent(String input) {
        String[] split = input.split(" /from | /to ");
        Event toDo = new Event(split[0], split[1], split[2]);
        toDoList.add(toDo);
        addTaskString(toDo.toString());
    }

    public static void addDeadline(String input) {
        String[] split = input.split(" /by ");
        Deadline toDo = new Deadline(split[0], split[1]);
        toDoList.add(toDo);
        addTaskString(toDo.toString());
    }

    public static void addTaskString(String taskToString) {
        System.out.println("     _________________________________________________________");
        System.out.println("     " + "Got it. I've added this task:");
        System.out.println("       " + taskToString);
        System.out.println("     " + "Now you have " + toDoList.size() + " tasks in the list.");
        System.out.println("     _________________________________________________________");
    }

    public static void main(String[] args) {
        helloMessage();
        echo();
    }
}
