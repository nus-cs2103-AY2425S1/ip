import Exceptions.DelphiException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Commands.Command;

import java.util.Scanner;

public class Delphi {
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;
    private final UI ui;

    public Delphi(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();
        ui = new UI();
    }
    public void run() {
        boolean isExitCommand = false;
        ui.welcomeMessage();
        System.out.println("current tasks:");
        taskList.loadStorageToTasks(this.storage);
        taskList.printTasks();
        Scanner scanner = new Scanner(System.in);
        while (!isExitCommand) {
            try {
                Command c = parser.parseInput(scanner.nextLine());
                c.execute(taskList, storage, ui);
                isExitCommand = c.exitBot();
            } catch (DelphiException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.goodbyeMessage();
    }
    public static void main(String[] args) {
        new Delphi("../ip/src/main/HardDisk.txt").run();
    }
}
