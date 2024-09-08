package miku;

import java.util.Scanner;

import miku.command.Command;
import miku.parser.CommandMikuParser;
import miku.utility.Response;
import miku.utility.Storage;
import miku.utility.TaskList;

/**
 * Executes the program.
 */
public class Run {
    private static Scanner scanner = new Scanner(System.in);
    private static CommandMikuParser commandParser = new CommandMikuParser();
    private static Response ui = new Response();
    private static Storage storage = new Storage();
    private static TaskList taskList;

    /**
     * Comments out the text-based launcher
     */
    //    public static void main(String[] args) {
    //        //Initialization of tasklist
    //        storage.init(taskList);
    //
    //        int counter = 0;
    //        while (counter < 10) {
    //            String userInput = scanner.nextLine();
    //            Command cmd = commandParser.parse(userInput);
    //            if (cmd != null) {
    //                cmd.execute(taskList, ui, storage);
    //                System.out.println(ui.getResponse());
    //            }
    //            if (cmd instanceof ExitCommand) {
    //                break;
    //            }
    //            counter += 1;
    //        }
    //
    //    }

    public String getResponse(String userInput) {
        taskList = new TaskList();
        storage.init(taskList);
        Command cmd = commandParser.parse(userInput);
        cmd.execute(taskList, ui, storage);
        return ui.getResponse();

    }

}
