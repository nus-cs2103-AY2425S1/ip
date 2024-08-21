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

    public static void printBar() {
        System.out.println(Orion.BAR);
    }

    public static void greet() {
        Orion.printBar();
        System.out.println(Orion.LOGO);
        Orion.printBar();

        System.out.println(Orion.INDENT + "Hello from Orion!");
        System.out.println(Orion.INDENT + "What do you want to talk about today?");
        Orion.printBar();
    }

    public static void echo(String command) {
        System.out.println(Orion.INDENT + command);
        Orion.printBar();
    }

    public static void sayGoodbye() {
        System.out.println(Orion.INDENT + "Bye! Hope to see you again soon!");
        Orion.printBar();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orion.greet();

        while (true) {
            String command = sc.nextLine();
            Orion.printBar();
            if (command.equals("bye")) {
                sc.close();
                break;
            } else {
                Orion.echo(command);
            }
        }

        Orion.sayGoodbye();
    }
}