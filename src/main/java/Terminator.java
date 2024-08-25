import command.*;
import task.TaskList;
import task.TaskIOHandler;

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

    private final TaskList taskList;
    private final CommandParser parser;

    private Terminator() {
        this.taskList = new TaskList();
        this.parser = new CommandParser();
    }

    public static Terminator build() {
        return new Terminator();
    }

    private static void printHorizontalLine() {
        System.out.print(HLINE);
    }

    private void greet() {
        String msg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        System.out.print(msg);
    }

    private void exit() {
        String exitMsg = HLINE + "Connection terminated. I will be back...\n" + HLINE;
        System.out.print(exitMsg);
    }

    private void mainEventLoop() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printHorizontalLine();
            try {
                Command command = this.parser.parse(input);
                command.execute(this.taskList.getTaskList());
                if (command instanceof TodoCommand
                    || command instanceof EventCommand
                    || command instanceof DeadlineCommand
                    || command instanceof DeleteCommand) {
                    taskList.printTasksRemaining();
                }
            } catch (DukeException de) {
                System.out.println("Error detected: " + de.getMessage());
            }
            printHorizontalLine();
            input = sc.nextLine();
        }
        sc.close();

        // Save data before exit
        taskList.writeToDisk();
    }

    public static void main(String[] args) {
        Terminator tChatbot = Terminator.build();
        try {
            boolean loadFileSuccess = TaskIOHandler.loadDataFromFile(tChatbot.taskList);
            System.out.println("Load file success: " + loadFileSuccess);
            if (loadFileSuccess) {
                tChatbot.greet();
                tChatbot.mainEventLoop();
                tChatbot.exit();
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
