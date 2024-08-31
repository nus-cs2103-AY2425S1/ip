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
            this.list = storage.load();
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
    public void echoOut() {

            String description = word;
            String[] parts = description.split(" ", 2);
            String command = parts[0];

        switch (command) {
            case "list":
                list.printList();
                System.out.println(ui.line());
                break;
            case "mark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to mark \n" + ui.line());
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        list.mark(index);
                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                System.out.print(ui.line());
                break;

            case "unmark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to unmark \n" + ui.line());
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        list.unmark(index);
                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                System.out.print(ui.line());
                break;
            case "find":
                try {
                    list.find(parts[1]).printList();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("No such items in the list!");
                }
                break;

            case "todo":
                try {
                    Task toDoTask = parser.parseToDoTask(parts[1]);
                    list.addTask(toDoTask);
                    System.out.print(ui.affirm() + toDoTask.getDescription() + "\n" + ui.line() +
                            String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Oh no! Please input a ToDo description!");
                }
                break;

            case "deadline":
                try {
                    Task deadlineTask = parser.parseDeadlineTask(parts[1]);
                    list.addTask(deadlineTask);
                    System.out.print(ui.affirm() + deadlineTask.getDescription() +
                                    "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect format of adding deadline tasks. " +
                            "Use '/by to specify the deadline after the task description");
                } catch (DateTimeParseException e) {
                    System.out.println("Please input the correct deadline format /by yyyy-MM-dd XXXX <- Time");
                }

                break;
            case "event":
                try {
                    Task eventTask = parser.parseEventTask(parts[1]);
                    list.addTask(eventTask);

                    System.out.print(ui.affirm() + eventTask.getDescription() +
                            "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect format of adding event tasks. " +
                            "Use '/from to specify the start and /to to specify the end " +
                            "after the task description");
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect format of adding event tasks timings. /from yyyy-MM-dd XXXX <- Time" +
                            "and /to yyyy-MM-dd XXXX <- Time\n");
                }

                break;
            case "delete":
                int index = Integer.parseInt(parts[1]) - 1;
                list.removeTask(index);
                break;

            case "bye":
                ui.goodbye();
                try {
                    storage.saveTasksToFile(this.list);
                } catch (IOException e) {
                    System.out.print("Unable to save chat data to local disk!");
                }
                break;
            default:
                ui.invalidCommand();
                break;
        }
    }
}
