import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class Lewis {
    private static boolean isExit = false;
    protected static TaskList taskList = new TaskList();

    private final static String[] commands = new String[] {"help","mark","unmark","echo","todo","deadline","event","bye","hello","list"};

    /**
     * Initialises the data for Lewis bot to function. This includes loading the tasklist from
     * the hard drive and the sorted command list that Lewis can parse.
     */
    private static void init() {
        Arrays.sort(commands);
        TaskList.load();
    }

    /**
     * Parses user input into commands.
     * @param input
     * @throws LewisException
     */
    private static void parse(String input) throws LewisException {
        StringBuilder str = new StringBuilder();
        int curr = 0;
        while (curr < input.length() && input.charAt(curr) != ' ') {
            str.append(input.charAt(curr));
            curr++;
        }
        curr++;
        String command = str.toString();
        switch (command) {
            case "help" -> {System.out.println("Here is the list of valid commands that I know now:");
                            System.out.println(Arrays.toString(commands));}
            case "hello" -> hello();
            case "todo" -> {
                StringBuilder todoDescript = new StringBuilder();
                while (curr < input.length()) {
                    todoDescript.append(input.charAt(curr));
                    curr++;
                }
                if (todoDescript.isEmpty() || todoDescript.toString().matches("^\\s*$")) throw new LewisException("Hey, a todo can't be empty!");
                taskList.add(new Todo(todoDescript.toString()));
                System.out.println("I've got it, I've added this todo to your tasklist.");
                System.out.println(taskList.get(taskList.length));
                System.out.printf("Now you have %d task(s) on our list!\n", taskList.length);
            }
            case "deadline" -> {
                StringBuilder deadlineDescript = new StringBuilder();
                StringBuilder deadline = new StringBuilder();
                while (curr < input.length() && input.charAt(curr) != '/'){
                    deadlineDescript.append(input.charAt(curr++));
                }
                if (deadlineDescript.isEmpty() || deadlineDescript.toString().matches("^\\s*$")) throw new LewisException("Hey, a task can't be empty!");
                if (curr + 3 >= input.length()) {throw new LewisException("Please type your command in the following form:\n" +
                        "deadline [String] /by [String]");
                }
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    temp.append(input.charAt(curr++));
                }
                if (!temp.toString().equals("/by")) {throw new LewisException("Please type your command in the following form:\n" +
                        "deadline [String] /by [String]");}
                curr++;
                while (curr < input.length()) {
                    deadline.append(input.charAt(curr++));
                }
                if (deadline.isEmpty() || deadline.toString().matches("^\\s*$")) throw new LewisException("Hey, fill in the deadline!");
                taskList.add(new Deadline(deadlineDescript.toString(), deadline.toString()));
                System.out.println("I've got it, I've added this deadline to your tasklist.");
                System.out.println(taskList.get(taskList.length));
                System.out.printf("Now you have %d task%s on our list!\n", taskList.length, (taskList.length == 1 ? "" : "s"));
            }
            case "event" -> {
                StringBuilder eventDescript = new StringBuilder();
                StringBuilder from = new StringBuilder();
                StringBuilder to = new StringBuilder();
                /* Find the description string */
                while (curr < input.length() && input.charAt(curr) != '/') {
                    eventDescript.append(input.charAt(curr++));
                }
                if (eventDescript.isEmpty() || eventDescript.toString().matches("^\\s*$")) throw new LewisException("Hey, an event can't be empty!");

                if (curr + 5 >= input.length()) {throw new LewisException("Please type your command in the following form:\n" +
                        "event [String] /from [String] /to [String]");}
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    temp.append(input.charAt(curr++));
                }
                if (!temp.toString().equals("/from")) {throw new LewisException("Please type your command in the following form:\n" +
                        "event [String] /from [String] /to [String]");}
                curr++;
                /* Find the 'from' string */
                while (curr < input.length() && input.charAt(curr) != '/') {
                    from.append(input.charAt(curr++));
                }
                if (from.isEmpty() || from.toString().matches("^\\s*$")) throw new LewisException("Hey, the \"from\" field can't be empty!");
                /* Checking for "/by" */
                if (curr + 3 >= input.length()) {throw new LewisException("Please type your command in the following form:\n" +
                        "event [String] /from [String] /to [String]");}
                StringBuilder temp2 = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    temp2.append(input.charAt(curr++));
                }
                if (!temp2.toString().equals("/to")) {throw new LewisException("Please type your command in the following form:\n" +
                        "event [String] /from [String] /to [String]");}
                curr++;

                /* Find the to string */
                while (curr < input.length()) {
                    to.append(input.charAt(curr));
                    curr++;
                }
                if (to.isEmpty() || to.toString().matches("^\\s*$")) throw new LewisException("Hey, the \"to\" field can't be empty!");
                taskList.add(new Event(eventDescript.toString(), from.toString(), to.toString()));
                System.out.println("I've got it, I've added this event to your tasklist.");
                System.out.println(taskList.get(taskList.length));
                System.out.printf("Now you have %d task(s) on our list!\n", taskList.length);
            }
            case "list" -> System.out.println(taskList.toString());
            case "bye","exit" -> {
                isExit = true;
                System.out.println("""
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠒⠒⠢⢄⡀⠀⠀⢠⡏⠉⠉⠉⠑⠒⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠀⠀⠀⠀⠀⠙⢦⠀⡇⡇⠀⠀⠀⠀⠀⠀⠈⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠊⠉⠉⠙⠒⢤⡀⠀⣼⠀⠀⢀⣶⣤⠀⠀⠀⢣⡇⡇⠀⠀⢴⣶⣦⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⢀⣠⠤⢄⠀⠀⢰⡇⠀⠀⣠⣀⠀⠀⠈⢦⡿⡀⠀⠈⡟⣟⡇⠀⠀⢸⡇⡆⠀⠀⡼⢻⣠⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⢀⠖⠉⠀⠀⠀⣱⡀⡞⡇⠀⠀⣿⣿⢣⠀⠀⠈⣧⣣⠀⠀⠉⠋⠀⠀⠀⣸⡇⠇⠀⠀⠈⠉⠀⠀⠀⢀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⣠⠏⠀⠀⣴⢴⣿⣿⠗⢷⡹⡀⠀⠘⠾⠾⠀⠀⠀⣿⣿⣧⡀⠀⠀⠀⢀⣴⠇⣇⣆⣀⢀⣀⣀⣀⣀⣤⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⣿⠀⠀⢸⢻⡞⠋⠀⠀⠀⢿⣷⣄⠀⠀⠀⠀⠀⣠⡇⠙⢿⣽⣷⣶⣶⣿⠋⢰⣿⣿⣿⣿⣿⣿⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⡿⡄⠀⠈⢻⣝⣶⣶⠀⠀⠀⣿⣿⣱⣶⣶⣶⣾⡟⠀⠀⠀⢈⡉⠉⢩⡖⠒⠈⠉⡏⡴⡏⠉⠉⠉⠉⠉⠉⠉⠉⡇⠀⠀⢀⣴⠒⠢⠤⣀
                        ⢣⣸⣆⡀⠀⠈⠉⠁⠀⠀⣠⣷⠈⠙⠛⠛⠛⠉⢀⣴⡊⠉⠁⠈⢢⣿⠀⠀⠀⢸⠡⠀⠁⠀⠀⠀⣠⣀⣀⣀⣀⡇⠀⢰⢁⡇⠀⠀⠀⢠
                        ⠀⠻⣿⣟⢦⣤⡤⣤⣴⣾⡿⢃⡠⠔⠒⠉⠛⠢⣾⢿⣿⣦⡀⠀⠀⠉⠀⠀⢀⡇⢸⠀⠀⠀⠀⠀⠿⠿⠿⣿⡟⠀⢀⠇⢸⠀⠀⠀⠀⠘
                        ⠀⠀⠈⠙⠛⠿⠿⠿⠛⠋⢰⡋⠀⠀⢠⣤⡄⠀⠈⡆⠙⢿⣿⣦⣀⠀⠀⠀⣜⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⢀⠃⠀⡸⠀⠇⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⢣⠀⠀⠈⠛⠁⠀⢴⠥⡀⠀⠙⢿⡿⡆⠀⠀⢸⠀⢸⢰⠀⠀⠀⢀⣿⣶⣶⡾⠀⢀⠇⣸⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡀⢇⠀⠀⠀⢀⡀⠀⠀⠈⢢⠀⠀⢃⢱⠀⠀⠀⡇⢸⢸⠀⠀⠀⠈⠉⠉⠉⢱⠀⠼⣾⣿⣿⣷⣦⠴⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⠘⡄⠀⠀⢹⣿⡇⠀⠀⠈⡆⠀⢸⠈⡇⢀⣀⣵⢨⣸⣦⣤⣤⣄⣀⣀⣀⡞⠀⣠⡞⠉⠈⠉⢣⡀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢃⠘⡄⠀⠀⠉⠀⠀⣠⣾⠁⠀⠀⣧⣿⣿⡿⠃⠸⠿⣿⣿⣿⣿⣿⣿⠟⠁⣼⣾⠀⠀⠀⠀⢠⠇⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⠹⣀⣀⣤⣶⣿⡿⠃⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⢻⣿⣷⣦⣤⣤⠎⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣤⣿⡿⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠀⠀⠀⠀⠀""");
                System.exit(0);
            }
            case "echo" -> {
                if (curr >= input.length()) {
                    throw new LewisException("You have to give me something to echo!");
                }
                StringBuilder echo = new StringBuilder();
                while (curr < input.length()) {
                    echo.append(input.charAt(curr));
                    curr++;
                }

                System.out.printf("\nLewis says: \"%s\"%n",echo);
            }
            case "mark" -> {
                StringBuilder taskToMark = new StringBuilder();
                while (curr < input.length() && input.charAt(curr) >= 48 && input.charAt(curr) <= 57) {
                    taskToMark.append(input.charAt(curr));
                    curr++;
                }
                int index;
                try {
                    index = Integer.parseInt(taskToMark.toString());
                    if (index >= 1 && index <= taskList.length) {taskList.update(index, Task.Status.DONE);}
                    else {throw new LewisException(String.format("Hey, please type a valid number between %d and %d.", 1, taskList.length));}
                }
                catch (NumberFormatException e) {
                    throw new LewisException(String.format("Hey, please type a valid number between %d and %d.", 1, taskList.length));
                }

            }
            case "unmark" -> {
                StringBuilder taskToMark = new StringBuilder();
                while (curr < input.length() && input.charAt(curr) >= 48 && input.charAt(curr) <= 57) {
                    taskToMark.append(input.charAt(curr));
                    curr++;
                }
                int index;
                try {
                    index = Integer.parseInt(taskToMark.toString());
                    taskList.update(index, Task.Status.NOT_DONE);
                }
                catch (NumberFormatException e) {
                    throw new LewisException(String.format("Hey, please type a valid number between %d and %d.", taskList.curr, taskList.end));
                }

            }
            case "delete" -> {
                StringBuilder taskToDelete = new StringBuilder();
                while (curr < input.length() && input.charAt(curr) >= 48 && input.charAt(curr) <= 57) {
                    taskToDelete.append(input.charAt(curr));
                    curr++;
                }
                int index;
                try {
                    index = Integer.parseInt(taskToDelete.toString());
                    if (index >= 1 && index <= taskList.length) {
                        System.out.printf("You are about to remove this task:\n%s",taskList.get(index));
                        taskList.remove(index);
                    }
                    else {throw new LewisException(String.format("Hey, please type a valid number between %d and %d.", 1, taskList.length));}
                }
                catch (NumberFormatException e) {
                    throw new LewisException(String.format("Hey, please type a valid number between %d and %d.", 1, taskList.length));
                }

            }
            default -> System.out.println("Oh dear, I've not learnt this command yet. Please try typing \"help\" for " +
                    "a list of valid commands.");
        }
    }

    private static void hello() {
        System.out.println("""
                Hello there! I am Lewis, a simple chatbot and I'm here to help you.
                In future versions, I'll be able to handle task scheduling and other services to make your life easier.
                Isn't that swell?""");
    }

    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
        Utilities.printLine();
        System.out.println("Hello! My name is Lewis, a chatbot.\nHow can I help you?");
        while(!isExit) {
            Utilities.printLine();
            //System.out.println("\n");
            String command = scanner.nextLine();
            try {
                parse(command);
            } catch (LewisException e) {
                System.out.println(e.getMessage());
            } finally {
                //System.out.println("\n");
                Utilities.printLine();
                System.out.println("What would you like to do next?");
            }
        }
        System.exit(0);
    }
}
