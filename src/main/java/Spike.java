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
        System.out.println("     _________________________________________________________");
        System.out.println("     Hello! I'm Spike\n     What can I do for you?");
        System.out.println("     _________________________________________________________");
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
            try {
                String input = scanner.nextLine().trim();
                String[] inputSplit = input.split(" ", 2);
                if (inputSplit.length == 1) {
                    throw new SpikeException("Please enter a valid command");
                }
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
                        throw new SpikeException("Please enter a valid command");
                }

                if (inputType == InputType.BYE) {
                    break;
                }
            } catch (SpikeException e) {
                System.out.println("     _________________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("     _________________________________________________________");
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

    public static void markTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            toDoList.get(index).markAsDone();
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    public static void unmarkTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            toDoList.get(index).unmark();
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    public static void checkDescription (String input) throws SpikeException {
        if (input.isEmpty() || !input.matches("\\S*?")) {
            throw new SpikeException("The description of a todo cannot be empty.");
        }
    }

    public static void addToDo(String input) throws SpikeException {
        checkDescription(input);
        ToDo toDo = new ToDo(input);
        toDoList.add(toDo);
        addTaskString(toDo.toString());
    }

    public static void addEvent(String input) throws SpikeException {
        checkDescription(input);
        String[] split = input.split(" /from | /to ");
        if (split.length == 3) {
            Event formattedEvent = new Event(split[0], split[1], split[2]);
            toDoList.add(formattedEvent);
            addTaskString(formattedEvent.toString());
        } else {
            throw new SpikeException("Please enter a valid event in the right format: " +
                    "event description /from start time /to end time");
        }
    }

    public static void addDeadline(String input) throws SpikeException {
        checkDescription(input);
        String[] split = input.split(" /by ");
        if (split.length == 2) {
            Deadline formattedDeadline = new Deadline(split[0], split[1]);
            toDoList.add(formattedDeadline);
            addTaskString(formattedDeadline.toString());
        } else {
            throw new SpikeException("Please enter a valid deadline in the right format: " +
                    "deadline description /by due date");
        }
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
