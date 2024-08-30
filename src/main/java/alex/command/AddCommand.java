package alex.command;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

import java.time.format.DateTimeParseException;


import alex.task.Task;
import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;
import alex.Parser;

public class AddCommand extends Command {
    private Scanner lineScanner;
    private String response;

    public AddCommand(Scanner lineScanner, String response) {
        this.lineScanner = lineScanner;
        this.response = response;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        ArrayList<String> arrOfStr = new ArrayList<>();
        Task task = new Task("", false);

        if (this.response.equals("todo")) {
            task = Parser.makeTodoTask(lineScanner, arrOfStr, false);
        } else if (this.response.equals("deadline")) {
            try {
                task = Parser.makeDeadlineTask(lineScanner, arrOfStr, false);
            } catch (DateTimeParseException e) {
                throw new AlexException("Oh no! Please provide the deadline in yyyy-mm-dd HHMM format " +
                        "e.g. 2024-05-19 1800");
            }
        } else if (this.response.equals("event")) {
            try {
                task = Parser.makeEventTask(lineScanner, arrOfStr, false);
            } catch (DateTimeParseException e) {
                throw new AlexException("Oh no! Please provide the start and end date and time in yyyy-mm-dd " +
                        "HHMM format e.g. 2024-05-19 1800");
            }

        }
        tasks.add(task, storage);
        ui.showMessage("Got it. I've added this task: ", task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
