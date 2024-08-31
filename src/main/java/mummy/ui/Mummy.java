package mummy.ui;

import mummy.task.TaskList;
import mummy.utility.Storage;

import java.io.IOException;
import java.util.Scanner;

public class Mummy {
    private static final String LOGO = " __  __\n"
            + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n"
            + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
            + "| |  | | |_| | | | | | | | | | | | |_| |\n"
            + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
            + "                                  |___/ \n";

    private static final String ioPath = "./data/mummy.txt";

    private Storage storage;

    private TaskList taskList;

    private Ui ui;

    private Mummy(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException e) {
            this.taskList = new TaskList();
        }

        this.ui = new Ui(LOGO, this.taskList, this.storage);
    }

    private void run() {
        this.ui.greet();
        this.ui.listen(new Scanner(System.in));
    }

    public static void main(String[] args) {
        new Mummy(ioPath).run();
    }
}
