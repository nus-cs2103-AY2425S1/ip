package yapper.app;

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String[] parsedLine = parser.parseLine(input);
            String command = parsedLine[0];

            if (command.equals("bye")) {
                return Ui.wrapText("Bye bye! :)");
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
                String desc = StringJoiner.join(parsedLine, 1, parsedLine.length, YapperConcern.DESC);
                Task task = new ToDo(desc);
                return taskList.addTask(task);
            } else if (command.equals("deadline")) {
                try {
                    int byIndex = -1;
                    for (int i = 0; i < parsedLine.length; i++) {
                        if (parsedLine[i].equals("/by")) {
                            byIndex = i;
                        }
                    }
                    if (byIndex == -1) {
                        throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE_BY])");
                    }
                    String desc = StringJoiner.join(parsedLine, 1, byIndex, YapperConcern.DESC);
                    String deadline = StringJoiner.join(parsedLine, byIndex + 1, parsedLine.length,
                            YapperConcern.DEADLINE_BY);
                    Task task = new Deadline(desc, deadline);
                    taskList.addTask(task);
                } catch (YapperException e) {
                    Ui.errorCaught(e.getMessage());
                }
            } else if (command.equals("event")) {
                try {
                    int fromIndex = -1;
                    int toIndex = -1;
                    for (int i = 0; i < parsedLine.length; i++) {
                        if (parsedLine[i].equals("/from")) {
                            fromIndex = i;
                        } else if (parsedLine[i].equals("/to")) {
                            toIndex = i;
                        }
                    }
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
                    }
                    String desc = StringJoiner.join(parsedLine, 1, fromIndex, YapperConcern.DESC);
                    String from = StringJoiner.join(parsedLine, fromIndex + 1, toIndex, YapperConcern.FROM);
                    String to = StringJoiner.join(parsedLine, toIndex + 1, parsedLine.length, YapperConcern.TO);
                    Task task = new Event(desc, from, to);
                    taskList.addTask(task);
                } catch (YapperException e) {
                    Ui.errorCaught(e.getMessage());
                }
            } else if (command.equals("find")) {
                String keyword = StringJoiner.join(parsedLine, 1, parsedLine.length, YapperConcern.KEYWORD);
                return taskList.findTasks(keyword);
            } else {
                throw new YapperException("Sorry! I don't know what you said. :(");
            }
        } catch (YapperException e) {
            return Ui.errorCaught(e.getMessage());
        }
        return "";
    }
}
