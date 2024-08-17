import java.util.Scanner;

public class Glados {
    private static final String HORIZONTAL_LINE = "\n" +"-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + " ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";
    private static Task[] list = new Task[100];    
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
                String query = input.split(" ")[0];
                if (query.equals("echo")) {
                    echo(input.substring(5, input.length()));
                } else if (query.equals("todo")) {
                    add(
                        TaskType.TODO, 
                        input.substring(5, input.length())
                    );
                } else if (query.equals("deadline")) {
                    add(
                        TaskType.DEADLINE, 
                        input.substring(9, input.length())
                    );
                } else if (query.equals("event")) {
                    add(
                        TaskType.EVENT, 
                        input.substring(6, input.length())
                    );
                } else if (query.equals("list")) {
                    list();
                } else if (query.equals("mark")) {
                    mark(Integer.valueOf(input.substring(5, input.length())));
                } else if (query.equals("unmark")) {
                    unmark(Integer.valueOf(input.substring(7, input.length())));
                } else {
                    error();
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

    public static void error() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I don't know what this command is...\n"
            + HORIZONTAL_LINE
        );
    }

    public static void add(TaskType taskType, String input) {
        switch (taskType) {
            case TODO:
                list[listIndex] = new Todo(input.trim());
                break;
            case EVENT:
                String[] eventInputs = input.split("/from");
                String[] dateRange = eventInputs[1].split("/to");
                list[listIndex] = new Event(eventInputs[0].trim(), dateRange[0].trim(), dateRange[1].trim());  
                break;
            case DEADLINE:
                String[] deadlineInputs = input.split("/by");
                list[listIndex] = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
                break;
            default:
                break;
        }
        listIndex++;
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have added the following task to the list...\n"
            + "\n" + list[listIndex - 1].toString() + "\n"
            + "\nNow you have " + listIndex + (listIndex == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE);
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GLaDOS: Here is the list...\n");
        for (int i = 0; i < listIndex; i++) {
            System.out.println(i + 1 + ". " + list[i].toString()); 
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(int index) {
        list[index - 1].mark();
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I've marked this task as done...\n"
            + "\n" + list[index - 1].toString() + "\n"
            + HORIZONTAL_LINE);
    }

    public static void unmark(int index) {
        list[index - 1].unmark();
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Oops, looks like this task is no longer done...\n"
            + "\n" + list[index - 1].toString() + "\n"
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
