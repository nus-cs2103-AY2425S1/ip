import java.util.Scanner;
public class Orion {

    public static final String LOGO = "             .__               \n"
                                    + "  ___________|__| ____   ____  \n"
                                    + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
                                    + "(  <_> )  | \\/  (  <_> )   |  \\\n"
                                    + " \\____/|__|  |__|\\____/|___|  /\n"
                                    + "                            \\/ \n";

    public static final String BAR = "______________________________________________\n";

    public static final String INDENT = "    ";

    private static boolean isOnline;
    private static Task[] tasks;
    private static int noTasks;

    private static void printBar() {
        System.out.println(Orion.BAR);
    }

    private static void printIndent(String message) {
        System.out.println(Orion.INDENT + message);
    }

    private static void greet() {
        Orion.printBar();
        System.out.println(Orion.LOGO);
        Orion.printBar();

        Orion.printIndent("Hello from Orion!");
        Orion.printIndent("What do you want to talk about today?");
        Orion.printBar();
    }

    private static void sayGoodbye() {
        Orion.printIndent("Bye! Hope to see you again soon!");
        Orion.printBar();
    }

    private static void list() {
        for (int i = 0; i < Orion.noTasks; i++) {
            String task = String.format("%d. %s", i + 1, Orion.tasks[i]);
            Orion.printIndent(task);
        }
    }

    private static void obey(String command) {
        switch (command) {
            case "bye":
                Orion.isOnline = false;
                break;
            case "list":
                Orion.list();
                Orion.printBar();
                break;
            default:
                Orion.tasks[Orion.noTasks] = new Task(command);
                Orion.noTasks++;
                Orion.printIndent("added: " + command);
                Orion.printBar();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orion.isOnline = true;
        Orion.tasks = new Task[100];
        Orion.noTasks = 0;
        Orion.greet();

        while (Orion.isOnline) {
            String command = sc.nextLine();
            Orion.printBar();
            Orion.obey(command);
        }

        sc.close();
        Orion.sayGoodbye();
    }
}