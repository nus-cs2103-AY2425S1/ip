package meerkatpack;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String[] strArray = thisTask.split(",", 5);
        switch (strArray.length) {
        case 3:
            try {
                taskList.createTodoTask(strArray[2], convertTaskCompletedStatus(strArray[1]));
            } catch (IOException e) {
                ui.showErrorWritingFileMessage();
            }
            break;
        case 4:
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");
                taskList.createDeadlineTask(strArray[2], LocalDateTime.parse(strArray[3], formatter), convertTaskCompletedStatus(strArray[1]));
            } catch (IOException e) {
                ui.showErrorWritingFileMessage();
            }
            break;
        case 5:
            try {
                taskList.createEventTask(strArray[2], strArray[3], strArray[4], convertTaskCompletedStatus(strArray[1]));
            } catch (IOException e) {
                ui.showErrorWritingFileMessage();
            }
            break;
        default:
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
            try {
                return taskList.createTodoTask(strArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showNeedMoreInfoTodoMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }

            // create new deadline task
        } else if (strArray[0].equalsIgnoreCase("deadline")) {
            try {
                String[] todoStringArray = taskName.split(" /by ");
                String dueDate = todoStringArray[1];
                String name = todoStringArray[0].split(" ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");
                return taskList.createDeadlineTask(name, LocalDateTime.parse(dueDate, formatter));
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showNeedMoreInfoDeadlineMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }

            // create new event task
        } else if (strArray[0].equalsIgnoreCase("event")) {
            try {
                String[] eventStringArray = taskName.split(" /from ");
                String[] duration = eventStringArray[1].split(" /to ");
                String name = eventStringArray[0].split(" ", 2)[1];
                return taskList.createEventTask(name, duration[0], duration[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showNeedMoreInfoEventMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }

            // to end program
        } else if (taskName.equalsIgnoreCase("bye")) {
            return ui.showGoodbyeMessage();

            // to display list of items
        } else if (taskName.equalsIgnoreCase("list")) {
            return ui.showList(taskList);

            // mark task as done
        } else if (strArray.length == 2 && strArray[0].equals("mark")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                return taskList.markTaskAsDone(taskNum);
            } catch (NumberFormatException e) {
                return ui.showTaskNonMarkableMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }
            // mark item as not done
        } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                return taskList.markTaskAsUndone(taskNum);
            } catch (NumberFormatException e) {
                return ui.showTaskNonUnmarkableMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }

            // delete task
        } else if (strArray.length == 2 && strArray[0].equals("delete")) {
            try {
                int taskNum = Integer.parseInt(strArray[1]);
                return taskList.deleteTask(taskNum);
            } catch (NumberFormatException e) {
                return ui.showUndeletableMessage();
            } catch (IOException e) {
                return ui.showErrorWritingFileMessage();
            }

            //find task
        } else if (strArray.length == 2 && strArray[0].equals("find")) {
            List<Task> matchingTaskList = taskList.findMatchingTasks(strArray[1]);
            return ui.showMatchingTasks(matchingTaskList);

            // invalid input
        } else {
            return ui.showNoIdeaMessage();
        }
    }

    private static boolean convertTaskCompletedStatus(String completionStatus) {
        return completionStatus.equalsIgnoreCase("m");
    }
}
