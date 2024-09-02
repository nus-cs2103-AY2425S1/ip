package miku;

import java.util.Scanner;

import miku.command.Command;
import miku.command.ExitCommand;
import miku.parser.CommandMikuParser;
import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.UI;

/**
 * Executes the program.
 */
public class Run {
    private static Scanner scanner = new Scanner(System.in);
    private static CommandMikuParser commandParser = new CommandMikuParser();
    private static UI ui = new UI();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        //Initialization of tasklist
        storage.init(taskList);
        ui.printLogo();
        ui.greet();

        int counter = 0;
        while (counter < 10) {
            String userInput = scanner.nextLine();
            Command cmd = commandParser.parse(userInput);
            if (cmd != null) {
                cmd.execute(taskList, ui, storage);
            }
            if (cmd instanceof ExitCommand) {
                break;
            }
            counter += 1;
        }

    }

}
