public class Ui {

    public static final String LINE = "________________________________________________";

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm LuToDo. What can I do for you?");
        showLine();
    }

    public static void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }



}
