import exception.*;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class LBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public LBot(String filePath) throws FileException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTasksFromFile());
        } catch (FileException e) {
            // dk man, do smt
            tasks = new TaskList();
        }
    }

    public void run() {
        // while loop here
        // try catch here also to catch all the exceptions
    }

    public static void main(String[] args) {

    }


}

