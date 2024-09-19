package utility;

import task.Task;
import task.TaskType;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Parsing user commands and interacting with the task list.
 */
public class Parser {

    private String trimCommand(String userInput, int size){
        return userInput.substring(size).trim();
    }

    /**
     * Parses the user input command and executes the appropriate action on the task list.
     *
     * @param tasks          The list of tasks to operate on.
     * @param userInput      The command input by the user.
     * @param taskList       The TaskList object to manage tasks.
     * @param dateTimeParser The Parser object used to parse date and time strings.
     * @param storage        The Storage object used to save user tasks.
     * @return A string representing the result of the executed command.
     */
    public String parseUICommand(ArrayList<Task> tasks, String userInput, TaskList taskList, Parser dateTimeParser, Storage storage){

        String output;

        if (Objects.equals(userInput, "bye")) {
            storage.save(tasks);
            System.exit(0);
            return null;
        }

        if (Objects.equals(userInput, "list")) {
            output = "Here are the tasks in your list:\n";
            StringBuilder result = new StringBuilder();
            if (tasks.isEmpty()){
                return "No Task Found";
            }
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return output + result;
        }

        if (userInput.startsWith("mark")) {
            try{
                int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                String result = taskList.markTask(tasks, taskNumber);
                if (result.startsWith("No")) {
                    assert result.equals("No Task Found") : "Wrong Output here";
                    return result;
                }else {
                    output = "Nice! I've marked this task as done:\n";
                    storage.save(tasks);
                    return output + result;
                }
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                return "The task number cannot be empty.";
            }
        }

        if (userInput.startsWith("unmark")) {
            try{
                int taskNumber = Integer.parseInt(trimCommand(userInput, 7));
                String result = taskList.unmarkTask(tasks, taskNumber);
                if (result.startsWith("No")) {
                    assert result.equals("No Task Found") : "Wrong Output here";
                    return result;
                }else{
                    output = "OK, I've marked this task as not done yet:\n";
                    storage.save(tasks);
                    return output + result;
                }
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                return "The task number cannot be empty.";
            }
        }

        if (userInput.startsWith("delete")) {
            try{
                int taskNumber = Integer.parseInt(trimCommand(userInput, 7));
                String result = taskList.deleteTask(tasks, taskNumber);
                if (result.startsWith("No")) {
                    assert result.equals("No Task Found") : "Wrong Output here";
                    return result;
                }else{
                    output = "Noted. I've removed this task:\n";
                    storage.save(tasks);
                    return output + result;
                }
            }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                return "The task number cannot be empty.";
            }
        }

        if (userInput.startsWith("todo")) {
            try{
                String desc = trimCommand(userInput, 5);
                if(!desc.isEmpty()){
                    String taskString = taskList.addTask(tasks, TaskType.TODO, desc);
                    if (taskString.startsWith("This")) {
                        return taskString;
                    }
                    storage.save(tasks);
                    return "Got it. I've added this task:\n" + taskString;
                }else{
                    return "The description of a todo cannot be empty.";
                }
            }catch(StringIndexOutOfBoundsException e){
                return "The description of a todo cannot be empty.";
            }
        }

        if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split(" /by ");
                String desc = parts[0].trim();
                if(!desc.isEmpty()){
                    LocalDateTime by = dateTimeParser.parseDateTime(parts[1].trim());
                    if (by == null){
                        return "Invalid Datetime format";
                    }
                    String taskString = taskList.addTask(tasks, TaskType.DEADLINE, desc, by);
                    if (taskString.startsWith("This")) {
                        return taskString;
                    }
                    storage.save(tasks);
                    return "Got it. I've added this task:\n" + taskString;
                }else{
                    return "The description and date of a deadline cannot be empty.";
                }
            }catch(StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                return "The description and date of a deadline cannot be empty.";
            }
        }

        if (userInput.startsWith("event")) {
            try{
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String desc = parts[0].trim();
                if(!desc.isEmpty()){
                    LocalDateTime from = dateTimeParser.parseDateTime(parts[1].trim());
                    LocalDateTime to = dateTimeParser.parseDateTime(parts[2].trim());
                    if (from == null || to == null){
                        return "Invalid Datetime format";
                    }
                    String taskString = taskList.addTask(tasks, TaskType.EVENT, desc, from, to);
                    if (taskString.startsWith("This")) {
                        return taskString;
                    }
                    storage.save(tasks);
                    return "Got it. I've added this task:\n" + taskString;
                }else{
                    return "The description and date of a event cannot be empty.";
                }
            }catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                return "The description and date of a event cannot be empty.";
            }
        }


        if (userInput.startsWith("find")) {
            try{
                String keyword = trimCommand(userInput, 5);
                if (keyword.isEmpty()){
                    return "The keyword to look for cannot be empty.";
                }
                String result = taskList.findTask(tasks, keyword);
                if (result.startsWith("No")){
                    return result;
                }else{
                    output = "Here are the matching tasks in your list:\n";
                    return output + result;
                }
            }catch(StringIndexOutOfBoundsException e){
                return "The keyword to look for cannot be empty.";
            }
        }

        return "I'm sorry, but I don't know what that means";
    }


    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string in the format "d/M/yyyy HHmm".
     * @return The parsed LocalDateTime object, or null if the format is invalid.
     */
    public LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }
}
