package meerkatpack;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import taskpack.Task;
import taskpack.TaskList;

/**
 * Used to parse inputs provided by user and calls the relevant task
 * creation methods.
 */
public class Parser {

    private static TaskList taskList = new TaskList();
    private static Ui ui;

    /**
     * Constructor for Parser.
     */
    public Parser() {
        ui = new Ui();
    }

    protected TaskList getTaskList() {
        return taskList;
    }

    /**
     * Reads the save file line by line, creates the relevant task with appropriate information.
     * @param thisTask Takes in a String that represents a task in the save file.
     */
    protected static void parseSaveFile(String thisTask) {
        String[] taskTypeArr = thisTask.split(",", 3);
        switch (taskTypeArr[0]) {
            case "t":
                parseTodoTask(taskTypeArr[2], taskTypeArr[1].equalsIgnoreCase("m"));
                break;
            case "d":
                parseDeadlineTask(taskTypeArr[2], taskTypeArr[1].equalsIgnoreCase("m"));
                break;
            case "e":
                parseEventTask(taskTypeArr[2], taskTypeArr[1].equalsIgnoreCase("m"));
                break;
            default:
                assert false;
                ui.showNoIdeaMessage();
        }
    }

    /**
     * Converts the string of input into a relevant task.
     * @param taskName String of input, the entire line given by user.
     */
    protected String parse(String taskName) {
        String[] strArray = taskName.split(" ", 2);

        // create new todo task
        if (strArray[0].equalsIgnoreCase("todo")) {
            if (strArray.length < 2) {
                return ui.showNeedMoreInfoMessage();
            }
            return parseTodoTask(strArray[1], false);
            // create new deadline task
        } else if (strArray[0].equalsIgnoreCase("deadline")) {
            if (strArray.length < 2) {
                return ui.showNeedMoreInfoMessage();
            }
            return parseDeadlineTask(strArray[1], false);
            // create new event task
        } else if (strArray[0].equalsIgnoreCase("event")) {
            if (strArray.length < 2) {
                return ui.showNeedMoreInfoMessage();
            }
            return parseEventTask(strArray[1], false);
            // to end program
        } else if (taskName.equalsIgnoreCase("bye")) {
            return ui.showGoodbyeMessage();
            // to display list of items
        } else if (taskName.equalsIgnoreCase("list")) {
            return ui.showList(taskList);
            // mark task as done
        } else if (strArray.length == 2 && strArray[0].equals("mark")) {
            return parseMarkTask(Integer.parseInt(strArray[1]));
            // mark item as not done
        } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
            return parseUnmarkTask(Integer.parseInt(strArray[1]));
            // delete task
        } else if (strArray.length == 2 && strArray[0].equals("delete")) {
            return parseDeleteTask(Integer.parseInt(strArray[1]));
            //find task
        } else if (strArray.length == 2 && strArray[0].equals("find")) {
            List<Task> matchingTaskList = taskList.findMatchingTasks(strArray[1]);
            return ui.showMatchingTasks(matchingTaskList);
            // invalid input
        } else {
            return ui.showNoIdeaMessage();
        }
    }

    public static String parseTodoTask(String taskName, boolean isMarked) {
        try {
            return taskList.createTodoTask(taskName, isMarked);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showNeedMoreInfoTodoMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
    }

    public static String parseDeadlineTask(String taskName, boolean isMarked) {
        try {
            String[] dueDateStringArray = taskName.split(" /by ");
            String dueDateString = dueDateStringArray[1];
            String name = dueDateStringArray[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");
            LocalDateTime dueDate = LocalDateTime.parse(dueDateString, formatter);
            return taskList.createDeadlineTask(name, dueDate, isMarked);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.showNeedMoreInfoDeadlineMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
        catch (DateTimeParseException e) {
            assert false;
            return ui.showDateTimeUnparseableMessage();
        }
    }

    public static String parseEventTask(String taskName, boolean isMarked) {
        try {
            String[] eventStringArray = taskName.split(" /from ");
            String[] duration = eventStringArray[1].split(" /to ");
            String name = eventStringArray[0];
            String start = duration[0];
            String end = duration[1];
            return taskList.createEventTask(name, start, end, isMarked);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showNeedMoreInfoEventMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
    }

    private String parseMarkTask(int taskNum) {
        try {
            return taskList.markTaskAsDone(taskNum);
        } catch (NumberFormatException e) {
            return ui.showTaskNonMarkableMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
    }

    private String parseUnmarkTask(int taskNum) {
        try {
            return taskList.markTaskAsUndone(taskNum);
        } catch (NumberFormatException e) {
            return ui.showTaskNonUnmarkableMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
    }

    private String parseDeleteTask(int taskNum) {
        try {
            return taskList.deleteTask(taskNum);
        } catch (NumberFormatException e) {
            return ui.showUndeletableMessage();
        } catch (IOException e) {
            assert false;
            return ui.showErrorWritingFileMessage();
        }
    }
}
