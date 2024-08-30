package ScoobyDoo;
import exception.InputFormatException;
import storage.Storage;
import task.Event;
import task.Task;
import task.Todo;
import task.Deadline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoobyDoo {
    private final String name = "Scooby-Doo";
    private TaskList taskList = new TaskList();
    private Storage storage;
    private UI ui;

    public ScoobyDoo(String FilePath) {
        storage = new Storage(FilePath);
        ui = new UI();
    }

    public void run () {
        ui.printFormattedResponse(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printErrorMessage("cannot parse data from file");
            taskList = new TaskList();
        }
        //loop
        String input;
        Scanner scanIn = new Scanner(System.in);
        while (true) {
            input = scanIn.nextLine();
            if (input.equals("bye")) {
                ui.printByeMessage();
                storage.writeFile(taskList.toFileFormatString());
                break;
            }

            if (input.equals("list")) {
                ui.printFormattedResponse(taskList.printList());
                continue;
            }

            if (Todo.matchTodo(input)) {
                try {
                    String description = Parser.getTodoDescription(input);
                    ui.printFormattedResponse(taskList.addTask(new Todo(description)));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            if (Deadline.matchDeadline(input)) {
                try {
                    String desription = Parser.getDeadlineDescription(input);
                    LocalDateTime byDate = Parser.getDeadlineDate(input);
                    ui.printFormattedResponse(taskList.addTask(new Deadline(desription, byDate)));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            if (Event.matchEvent(input)) {
                try {
                    String description = Parser.getEventDescription(input);
                    LocalDateTime[] fromToDate = Parser.getEventFromAndToDate(input);
                    ui.printFormattedResponse(taskList.addTask(new Event(description, fromToDate[0], fromToDate[1])));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("mark")) {
                try {
                    int num = Task.matchesMark(input);
                    ui.printFormattedResponse(taskList.markTask(num));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("unmark")) {
                try {
                    int num = Task.matchesUnmark(input);
                    ui.printFormattedResponse(taskList.unmarkTask(num));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("delete")) {
                try {
                    int i = TaskList.getDeleteNumber(input);
                    ui.printFormattedResponse(taskList.deleteTask(i));
                } catch (InputFormatException e) {
                    ui.printErrorMessage(e.getMessage());
                }
                continue;
            }

            else {
                ui.printFormattedResponse("The available inputs are\n deadline\n event\n todo\n mark\n unmark\n list\n delete\n bye");
            }
        }
        scanIn.close();
    }




    public static void main(String[] args) {
        new ScoobyDoo("data/tasks.txt").run();
    }
}

