package lutodo.ui;

import java.util.Scanner;

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
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }



}
