package mahesh;

import java.util.Scanner;

import mahesh.command.Command;
import mahesh.util.MaheshException;
import mahesh.util.Parser;
import mahesh.util.Storage;
import mahesh.util.TaskList;
import mahesh.util.Ui;

public class Mahesh {

    private String path ;

    public Mahesh(String path) {
        this.path = path;
    }

    public void run() {
        Ui.printStartupMessage();
        Storage store = new Storage(this.path);
        TaskList list = store.retrieveData();
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            Parser parserObj = new Parser(list, store);
            try {
                Command command = parserObj.parse(originalInput);
                if (command != null) {
                    command.execute();
                    exit = command.isExit();
                }
            } catch (MaheshException err) {
                Ui.printErr(err);
            }
            store.updateData(list);
        }
        scan.close();
    }

    public static void main(String[] args) {
        Mahesh app = new Mahesh("../../../../data/mahesh.txt");
        app.run();
    }
}
