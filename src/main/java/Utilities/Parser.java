package Utilities;

import Task.Task;
import Task.TaskType;
import Task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Parsing user commands and interacting with the task list.
 */
public class Parser {

    /**
     * Parses the user input command and executes the appropriate action on the task list.
     *
     * @param tasks          The list of tasks to operate on.
     * @param userInput      The command input by the user.
     * @param taskList       The TaskList object to manage tasks.
     * @param ui             The UI object for displaying messages.
     * @param dateTimeParser The Parser object used to parse date and time strings.
     * @return true if the command is 'bye', indicating the application should terminate; false otherwise.
     */
    public Boolean parseCommand(ArrayList<Task> tasks, String userInput, TaskList taskList, Ui ui, Parser dateTimeParser){

        if (Objects.equals(userInput, "bye")) {
            ui.endMsg();
            Storage storage = new Storage();
            storage.save(tasks);
            return true;
        }

        if (Objects.equals(userInput, "list")) {
            ui.printTask(tasks);
            return false;
        }

        if (userInput.startsWith("mark")) {
            try{
                int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                taskList.markTask(tasks, taskNumber);
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                ui.blankMsg("number");
            }
            return false;
        }

        if (userInput.startsWith("unmark")) {
            try{
                int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                taskList.unmarkTask(tasks, taskNumber);
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                ui.blankMsg("number");
            }
            return false;
        }

        if (userInput.startsWith("delete")) {
            try{
                int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                taskList.deleteTask(tasks, taskNumber);
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                ui.blankMsg("number");
            }
            return false;
        }

        if (userInput.startsWith("todo")) {
            try{
                String desc = userInput.substring(5).trim();
                if(!desc.isEmpty()){
                    taskList.addTask(tasks, TaskType.TODO, desc);
                }else{
                    ui.blankMsg("todo");
                }
            }catch(StringIndexOutOfBoundsException e){
                ui.blankMsg("todo");
            }
            return false;
        }

        if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split(" /by ");
                String desc = parts[0].trim();
                if(!desc.isEmpty()){
                    taskList.addTask(tasks, TaskType.DEADLINE, desc, dateTimeParser.parseDateTime(parts[1].trim()));
                }else{
                    ui.blankMsg("deadline");
                }
            }catch(StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                ui.blankMsg("deadline");
            }
            return false;
        }

        if (userInput.startsWith("event")) {
            try{
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String desc = parts[0].trim();
                if(!desc.isEmpty()){
                    taskList.addTask(tasks, TaskType.EVENT, desc, dateTimeParser.parseDateTime(parts[1].trim()), dateTimeParser.parseDateTime(parts[2].trim()));
                }else{
                    ui.blankMsg("event");
                }
            }catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                ui.blankMsg("event");
            }
            return false;
        }

        ui.defaultMsg();
        return false;
    }


    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string in the format 'd/M/yyyy HHmm'.
     * @return The parsed LocalDateTime object, or null if the format is invalid.
     */
    public LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            new Ui().invalidDateMsg();
            return null;
        }
    }
}
