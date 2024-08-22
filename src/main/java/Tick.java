import java.util.Scanner;

public class Tick {
    private static final String separator = "____________________________________________________________\n";
    private static final String botName = "Tick";
    private String[] checklist = new String[100];

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
        System.out.printf("added: %s\n", command);
        System.out.println(Tick.separator);
    }

    public void displayList() {
        System.out.print(Tick.separator);
        for (int i = 0; i < 100; i++) {
            if (this.checklist[i] == null) {
                break;
            }
            System.out.printf("%d. %s\n", i + 1, this.checklist[i]);
        }
        System.out.println(Tick.separator);
    }

    private void addToList(String command) {
        for (int i = 0; i < 100; i++) {
            if (this.checklist[i] == null) {
                this.checklist[i] = command;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Tick bot = new Tick();
        bot.greet();
        while (true) {
            Scanner scn = new Scanner(System.in);
            String command = scn.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                bot.displayList();
                continue;
            }
            bot.echo(command);
            bot.addToList(command);
        }
        bot.exit();
    }
}