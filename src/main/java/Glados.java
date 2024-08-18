import java.util.Scanner;
import java.util.ArrayList;

public class Glados {
    private static final String HORIZONTAL_LINE = "\n" 
        + "-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + " ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";
    private static ArrayList<Task> taskList = new ArrayList<>();  
    private static int listIndex = 0;
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else {
                try {
                    String query = input.split(" ")[0];
                    if (query.equals("echo")) {
                        echo(input.substring(4, input.length()));
                    } else if (query.equals("todo")) {
                        add(
                            TaskType.TODO, 
                            input.substring(4, input.length())
                        );
                    } else if (query.equals("deadline")) {
                        add(
                            TaskType.DEADLINE, 
                            input.substring(8, input.length())
                        );
                    } else if (query.equals("event")) {
                        add(
                            TaskType.EVENT, 
                            input.substring(5, input.length())
                        );
                    } else if (query.equals("list")) {
                        list();
                    } else if (query.equals("mark")) {
                        mark(Integer.valueOf(input.substring(5, input.length())));
                    } else if (query.equals("unmark")) {
                        unmark(Integer.valueOf(input.substring(7, input.length())));
                    } else if (query.equals("delete")) {
                        delete(Integer.valueOf(input.substring(7, input.length())));
                    } else {
                        throw new CommandNotFoundException();
                    }
                } catch (GladosException e) {
                    error(e);
                }
            }
        }
        sc.close();
    }

    public static void greet() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nHello, welcome to the Aperture Science computer-aided enrichment center! My name is:\n"
            + LOGO
            + "What task would you like me to perform today?\n"
            + HORIZONTAL_LINE
        );
    }

    public static void echo(String input) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: " + input + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void error(GladosException e) {
        System.out.println(
            HORIZONTAL_LINE
            + "\n" + e.getMessage() + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void add(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            taskList.add(new Todo(todoDescription));
            break;
        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split(" /from ");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);
            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }
            String[] dateRange = eventInputs[1].split(" /to ");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }
            taskList.add(new Event(eventInputs[0].trim(), dateRange[0].trim(), dateRange[1].trim()));
            break;
        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split(" /by ");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);
            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }
            taskList.add(new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim()));
            break;
        default:
            break;
        }
        listIndex++;
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have added the following task to the list...\n"
            + "\n" + taskList.get(listIndex - 1).toString() + "\n"
            + "\nNow you have " + listIndex + (listIndex == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }

    public static void delete(int index) throws TaskNotFoundException{
        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.remove(index - 1);
        listIndex--;
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have removed the following task from the list...\n"
            + "\n" + task.toString() + "\n"
            + "\nNow you have " + listIndex + (listIndex == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
         if (description.equals("")) {
            throw new DescriptionNotFoundException();
         }
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GLaDOS: Here is the list...\n");
        for (int i = 0; i < listIndex; i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString()); 
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(int index) {
        Task task = taskList.get(index - 1);
        task.mark();
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I've marked this task as done...\n"
            + "\n" + task.toString() + "\n"
            + HORIZONTAL_LINE);
    }

    public static void unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmark();
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Oops, looks like this task is no longer done...\n"
            + "\n" + task.toString() + "\n"
            + HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Goodbye, user.\n"
            + HORIZONTAL_LINE
        );
    }

}
