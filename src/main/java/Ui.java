import java.util.Scanner;

public class Ui {
    Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String welcomeMessage =
          "\n" +
          " ___ .-.     .---.   ___  ___    .---.   ___ .-.     .---.\n" +
          "(   )   \\   / .-, \\ (   )(   )  / .-, \\ (   )   \\   / .-,\\\n" +
          " |  .-. .  (__) ; |  | |  | |  (__) ; |  |  .-. .  (__) ; |\n" +
          " | |  | |    .'  |  | |  | |   .'  |  |  |  | |    .'  |\n" +
          " | |  | |   / .'| |  | '  | |  / .'|  |  | |  | |   / .'| |\n" +
          " | |  | |  | /  | |  '  -' |  | /  |  |  | |  | |  | /  | |\n" +
          " | |  | |  ; |  ; |   .__. |  ; |  ;  |  | |  | |  ; |  ; |\n" +
          " | |  | |  ' -'  |   ___ | |  ' -'    |  | |  | |  ' -'  |\n" +
          "(___)(___) .__.'_.  (   )' |  .__.'_.   (___)(___) .__.'_.\n" +
          "                      ; -' '\n" +
          "                       .__.'";
    public void showLoadingError() {

    }

    public void showWelcomeMessage() {
        System.out.println("Hello from" + welcomeMessage);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nayana");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void printAddTask(Task task,int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list",size));
    }

    public void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list", size));
    }

    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void printUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void printTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void printExit() {
        System.out.print("Bye!!! Hope to help you again soon!\n");

        scanner.close();
    }

}
