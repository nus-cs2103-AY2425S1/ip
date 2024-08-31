import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Run {
    public static Scanner scanner = new Scanner(System.in);
    public static CommandMikuParser commandParser = new CommandMikuParser();
    public static UI ui = new UI();
    public static Storage storage = new Storage();
    public static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        //Initialization of tasklist
        storage.init(taskList);
        ui.printLogo();
        ui.greet();

        int counter = 0;
        while (counter < 10){
            String userInput = scanner.nextLine();
            Command cmd = commandParser.parse(userInput);
            if (cmd != null){
                cmd.execute(taskList, ui, storage);
            }
            if (cmd instanceof ExitCommand){
                break;
            }
            counter += 1;
        }

    }

}
