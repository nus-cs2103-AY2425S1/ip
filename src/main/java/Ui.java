import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    private static final String LOGO = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
            "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
            "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
            "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
            "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
            "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
            "| |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'";
    private static final String HORIZONTAL_LINE = "________________________________________";
    private static final String GOODBYE = "   ___                _ _                  _ \n" +
            "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
            " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
            "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
            "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
            "                              |___/          ";

    public static void displayGreeting() {
        System.out.println("Hello! I'm\n" + LOGO + "\nWhat can I do for you?");
        Ui.insertLine();
    }

    public static void displayGoodbye() {
        Ui.insertLine();
        System.out.println(GOODBYE);
        Ui.insertLine();
    }

    public static void insertLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showError(String message) {
        Ui.insertLine();
        System.out.println("ERROR: " + message);
        Ui.insertLine();
    }

    public static void showDateTimeParseError(String message) {
        Ui.insertLine();
        System.out.println("Date format is wrong, please input dd-mm-yyyy hhmm: " + message);
        Ui.insertLine();
    }

    public static void showUnexpectedError(String message) {
        Ui.insertLine();
        System.out.println("UNEXPECTED ERROR: " + message);
        Ui.insertLine();
    }

    public static void showTaskList(TaskList tasks) {
        int length = tasks.size();
        Ui.insertLine();
        for (int i = 0; i < length; i++) {
            Task t = tasks.get(i);
            int num = i + 1;
            System.out.println(num + "." + t);
        }
        Ui.insertLine();
    }

    public static void printAddDeadline(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }

    public static void printEvent(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }

    public void showLoadingError(String message) {
        System.out.println("LOADING ERROR: " + message);
    }

    public String nextInput(){
        return this.scanner.nextLine();
    }

    public static void printAddTodo(TaskList tasks) {
        int num = tasks.size();
        Ui.insertLine();
        System.out.println("Got it. I've added this task:\n" + tasks.get(num - 1));
        System.out.println("Now you have " + num + " tasks in the list.");
        Ui.insertLine();
    }

}
