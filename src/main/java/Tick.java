import java.util.Scanner;

public class Tick {
    private static final String separator = "____________________________________________________________\n";
    private static final String botName = "Tick";

    public void greet() {
        System.out.print(Tick.separator);
        System.out.printf("Hello! I'm %s\n", Tick.botName);
        System.out.println("What can I do for you?");
        System.out.println(Tick.separator);
    }

    public void exit() {
        System.out.print(Tick.separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Tick.separator);
    }

    public void echo(String command) {
        System.out.print(Tick.separator);
        System.out.println(command);
        System.out.println(Tick.separator);
    }

    public static void main(String[] args) {
        Tick bot = new Tick();
        bot.greet();
        while (true) {
            Scanner scn = new Scanner(System.in);
            String command = scn.nextLine();
            if (command.equals("bye")) {
                break;
            }
            bot.echo(command);
        }
        bot.exit();
    }
}
