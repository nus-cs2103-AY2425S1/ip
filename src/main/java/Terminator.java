import command.Command;
import command.CommandParser;
import command.DukeException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Terminator {
    private static final String HLINE = "____________________________________________________________\n";
    private static final String LOGO =
                  """
                                     <((((((\\\\\\
                                     /      . }\\
                                     ;--..--._|}
                  (\\                 '--/\\--'  )
                   \\\\                | '-'  :'|
                    \\\\               . -==- .-|
                     \\\\               \\.__.'   \\--._
                     [\\\\          __.--|       //  _/'--.
                     \\ \\\\       .'-._ ('-----'/ __/      \\
                      \\ \\\\     /   __>|      | '--.       |
                       \\ \\\\   |   \\   |     /    /       /
                        \\ '\\ /     \\  |     |  _/       /
                         \\  \\       \\ |     | /        /
                          \\  \\      \\        /
                  """;
    private final ArrayList<Task> todoList;
    private final CommandParser parser;
    private Terminator() {
        this.todoList = new ArrayList<>();
        this.parser = new CommandParser();
    }
    public static Terminator build() {
        return new Terminator();
    }

    public static void printHorizontalLine() {
        System.out.print(HLINE);
    }

    public void greet() {
        String msg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        System.out.println(msg);
    }

    public void exit() {
        String exitMsg = HLINE + "Terminating session. I will be back...\n" + HLINE;
        System.out.println(exitMsg);
    }
    public void mainEventLoop() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printHorizontalLine();
            try {
                Command command = this.parser.parse(input);
                command.execute(this.todoList);
            } catch (DukeException de) {
                System.out.println("Error detected: " + de.getMessage());
            }
            printHorizontalLine();
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Terminator tChatbot = Terminator.build();
        tChatbot.greet();
        tChatbot.mainEventLoop();
        tChatbot.exit();
    }
}
