import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        String logo = """
                ___________        .__     .___               \s
                \\_   _____/_______ |__|  __| _/_____    ___.__.
                 |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |
                 |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |
                 \\___  /    |__|   |__|\\____ | (____  / / ____|
                     \\/                     \\/      \\/  \\/    \s
                """;

        System.out.println("Loading........\n" + logo);
        printLine();

        System.out.println("""
                Hello! I'm Friday!
                What would you like to do?
                """);

        TaskList master = new TaskList(100);
        boolean bye = false;
        Scanner sc = new Scanner(System.in);

        while(!bye) {
            System.out.print("Your input > ");
            String[] parsed = getInput(sc);
            printLine();
            switch (parsed[0]) {
                case "Bye":
                case "bye":
                    bye = true;
                    break;
                case "Quit":
                case "quit":
                case "Exit":
                case "exit":
                    System.out.println("Friday > Type \"bye\" or \"Bye\" to exit");
                    printLine();
                    break;
                case "List":
                case "list":
                    if (master.getSize() <= 0) {
                        System.out.println("Friday > No tasks in here! Try adding something!\n");
                        break;
                    }
                    System.out.println("Friday > Here's everything!\n" + master);
                    break;
                case "Remove":
                case "remove":
                    try {
                        int index = Integer.parseInt(parsed[1]);
                        master.removeTask(index-1);
                        break;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to remove the task\n");
                        break;
                    }
                case "Add":
                case "add":
                    try {
                        master.addTask(parsed[1]);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Friday > Try doing add <task name> \n");
                        break;
                    } finally {
                        printLine();
                    }
                case "mark":
                case "unmark":
                    try {
                        int index = Integer.parseInt(parsed[1]);
                        master.doneTask(parsed[0], index-1);
                        printLine();
                        break;
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Friday > Input the task number to mark/unmark the task\n");
                        break;
                    }
                case "help":
                case "Help":
                    System.out.println("""
                        Friday > Hello! I'm Friday! Your personal chatbot for ensuring you get things done by Friday ;)
                        To create a new task, type "add <task name>" and follow the instructions.
                        
                        Other commands:
                        help            displays this page
                        list            lists all tasks available
                        mark <index>    mark a task as completed
                        unmark <index>  mark a task as incomplete
                        remove <index>  remove task from list
                        bye             exit program
                        """);
                    printLine();
                    break;
                default:
                    System.out.println("Friday > Hmm...you can't do that. Try add/remove <task> or \"help\" for more options \n");
                    printLine();
                    break;
            }
        }
        terminate();
        sc.close();
    }

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public static void terminate() {
        System.out.println("Friday > Bye! See you soon!");
        printLine();
    }

    public static String[] getInput(Scanner sc) {
        String input = sc.nextLine();
        return input.split(" ", 2);
    }
}