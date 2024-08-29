import Exceptions.DelphiException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Commands.BreakCommand;
import Commands.Command;
import Commands.MarkTaskCommand;

import java.util.Scanner;

public class Delphi {
    private TaskList taskList;
    private Storage storage;

    private Parser p;

    private UI ui;

    public Delphi(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        p = new Parser();
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
                Command c = p.parseInput(scanner.nextLine());
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
