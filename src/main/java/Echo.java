import parser.Parser;
import tasks.Task;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Echo {
    private String word;
    private TaskList list;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Echo() {
        this.storage = new Storage("data/chatHistory.txt");
        try {
            this.list = storage.loadTasks();
        } catch (IOException e) {
            this.list = new TaskList();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Sets the word for the echo object.
     * @param word used to initialise the word of an echo object.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Main logic for the entire chatbot. Handles all the different commands.
     */
    public String echoOut() {

        String description = word;
        String[] parts = description.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            return list.printList() + "\n" + ui.line();
        case "mark":
            if (parts.length < 2) {
                return "Please specify the task number to mark \n" + ui.line();
            } else {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    assert index < 0 : "Please enter a valid task number\n";
                    return list.mark(index) + ui.line();
                } catch (NumberFormatException e) {
                    return ui.NumberFormatExceptionMessage() + ui.line();
                }
            }
        case "unmark":
            if (parts.length < 2) {
                return "Please specify the task number to unmark \n" + ui.line();
            } else {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    assert index < 0 : "Please enter a valid task number\n";
                    return list.unmark(index) + ui.line();
                } catch (NumberFormatException e) {
                    return ui.NumberFormatExceptionMessage() + ui.line();
                }
            }
        case "find":
            try {
                return list.find(parts[1]).printList() + ui.line();
            } catch (StringIndexOutOfBoundsException e) {
                return "No such items in the list!\n" + ui.line();
            }

        case "todo":
            try {
                Task toDoTask = parser.parseToDoTask(parts[1]);
                list.addTask(toDoTask);
                return ui.affirm() + toDoTask.getDescription() + "\n" + ui.line() +
                        String.format("Now you have %d tasks in the list\n", list.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Oh no! Please input a ToDo description!";
            }

        case "deadline":
            try {
                Task deadlineTask = parser.parseDeadlineTask(parts[1]);
                list.addTask(deadlineTask);
                return ui.affirm() + deadlineTask.getDescription() +
                        "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size());
            } catch (StringIndexOutOfBoundsException e) {
                return ui.DeadlineOutOfBoundsExceptionMessage();
            } catch (DateTimeParseException e) {
                return ui.DeadlineDateTimeParseExceptionMessage();
            }

        case "event":
            try {
                Task eventTask = parser.parseEventTask(parts[1]);
                list.addTask(eventTask);

                return ui.affirm() + eventTask.getDescription() +
                        "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size());
            } catch (StringIndexOutOfBoundsException e) {
                return ui.EventOutOfBoundsExceptionMessage();
            } catch (DateTimeParseException e) {
                return ui.EventDateTimeParseExceptionMessage();
            }

        case "delete":
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index < 0 : "Please input a valid task number!";
                return list.removeTask(index);
            } catch (NumberFormatException e) {
                return ui.NumberFormatExceptionMessage() + ui.line();
            }

        case "bye":
            try {
                storage.saveTasksToFile(this.list);
                return ui.goodbye();
            } catch (IOException e) {
                return "Unable to save chat data to local disk!\n";
            }
        default:
            return ui.invalidCommand();
        }
    }
}
