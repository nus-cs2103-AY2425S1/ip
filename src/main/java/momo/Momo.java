package momo;

import momo.command.*;

import momo.task.Deadline;
import momo.task.Event;
import momo.task.TaskList;
import momo.task.Todo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;


public class Momo {

    public static final String FILE_PATH = "data/momo.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Momo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.showGreeting();
        String input = ui.getUserInput().trim();

        while (true) {
            try {
                CommandType command = Parser.parseInput(input);
                if (command == CommandType.BYE) {
                    ui.showFarewell();
                }
                else if (command == CommandType.LIST) {
                    ui.printList(tasks);
                }
                else if (command == CommandType.FIND) {
                    FindCommand.run(input, tasks);
                }
                else if (command == CommandType.MARK) {
                    MarkCommand.run(input, tasks, storage);
                }
                else if (command == CommandType.UNMARK) {
                    UnmarkCommand.run(input, tasks, storage);
                }
                else if (command == CommandType.DELETE) {
                    DeleteCommand.run(input, tasks, storage);
                }
                else {
                    if (command == CommandType.TODO) {
                        TodoCommand.run(input, storage, tasks);
                    }
                    else if (command == CommandType.DEADLINE) {
                        DeadlineCommand.run(input, storage, tasks);
                    }

                    else if (command == CommandType.EVENT) {
                        EventCommand.run(input, storage, tasks);
                    }
                }
            }
            catch (MomoException e){
                System.out.println(e.getMessage());
            }
            input = ui.getUserInput();
        }
    }

    public static void main(String[] args) throws MomoException {
        new Momo(FILE_PATH).run();
    }


}










