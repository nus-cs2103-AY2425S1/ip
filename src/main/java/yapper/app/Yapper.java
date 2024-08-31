package yapper.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import yapper.exceptions.EmptyDescException;
import yapper.exceptions.YapperException;
import yapper.exceptions.YapperFormatException;

/**
 * The main class for the Yapper chatbot that stores ToDos, Deadlines, and Events in a taskLit
 */
public class Yapper {

    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Yapper object with the specified file path for storage.
     *
     * @param filePath the file path for storing task data
     */
    public Yapper(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.readFile(), filePath);
    }

    /**
     * Generates a response for the user's chat message based on the command given.
     * If user types "bye", the program will display a message before closing itself.
     */
    public String getResponse(String input) {
        try {
            String[] parsedLine = parser.parseLine(input);
            String command = parsedLine[0];

            if (command.equals("bye")) {
                String response = Ui.wrapText("Bye bye! :)");
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.seconds(1),
                        event -> Platform.exit()
                ));
                timeline.setCycleCount(1);
                timeline.play();
                return response;
            } else if (command.equals("hi") || command.equals("hello")) {
                return Ui.wrapText("Hello! :)");
            } else if (command.equals("list")) {
                return taskList.listTasks();
            } else if (command.equals("mark") || command.equals("unmark")) {
                if (parsedLine.length < 2) {
                    throw new YapperException("Format error: mark/unmark [TASK_NUMBER]");
                }
                String taskNumber = parsedLine[1];
                return taskList.markTask(command, taskNumber);
            } else if (command.equals("delete")) {
                if (parsedLine.length < 2) {
                    throw new YapperException("Format error: delete [TASK_NUMBER]");
                }
                String taskNumber = parsedLine[1];
                return taskList.deleteTask(taskNumber);
            } else if (command.equals("todo")) {
                try {
                    String desc = input.split("todo")[1].trim();
                    return taskList.addTask(new ToDo(desc));
                } catch (IndexOutOfBoundsException e) {
                    throw new YapperFormatException("(Format: todo [DESC])");
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] deadline = input.split("/by");
                    String[] desc = deadline[0].split("deadline");
                    return taskList.addTask(new Deadline(desc[1].trim(), deadline[1].trim()));
                } catch (IndexOutOfBoundsException e) {
                    throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE_BY])");
                }
            } else if (command.equals("event")) {
                try {
                    String[] toDate = input.split("/to");
                    String[] fromDate = toDate[0].split("/from");
                    String[] desc = fromDate[0].split("event");
                    return taskList.addTask(new Event(desc[1].trim(), fromDate[1].trim(), toDate[1].trim()));
                } catch (IndexOutOfBoundsException e) {
                    throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
                }
            } else if (command.equals("find")) {
                try {
                    String keyword = input.split("find")[1].trim();
                    return taskList.findTasks(keyword);
                } catch (IndexOutOfBoundsException e) {
                    throw new EmptyDescException("(Format: event find [KEYWORD])");
                }
            } else {
                throw new YapperException("Sorry! I don't know what you said. :(");
            }
        } catch (YapperException e) {
            return Ui.errorCaught(e.getMessage());
        }
    }
}
