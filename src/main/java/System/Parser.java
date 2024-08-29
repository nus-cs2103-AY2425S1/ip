package System;

import Commands.Deadlines;
import Commands.Events;
import Commands.TaskList;
import Commands.ToDos;

import java.io.IOException;
import java.time.LocalDateTime;

public class Parser {
    Ui ui = new Ui();
    DateTimeSystem ds = new DateTimeSystem();

    /**
     * Checks if the provided input string equals to the keyword "bye".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "bye"; false otherwise.
     */
    public boolean containBye(String input) {
        return input.equals(("bye"));
    }

    /**
     * Checks if the provided input string equals, ignoring case sensitivity, to the keyword "list".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "list"; false otherwise.
     */
    public boolean containList(String input) {
        return input.equalsIgnoreCase("list");
    }

    /**
     * Checks if the provided input string contains the keyword "mark".
     *
     * @param input Input string  to be checked.
     * @return true if the input string contains the substring "mark"; false otherwise.
     */
    public boolean containMark(String input) {
        return input.contains("mark");
    }

    /**
     * Checks if the provided input string contains the keyword "unmark".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "unmark"; false otherwise.
     */
    public boolean containUnmark(String input) {
        return input.contains("unmark");
    }

    /**
     * Checks if the provided input string contains the keyword "todo".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "todo"; false otherwise.
     */
    public boolean containToDo(String input) {
        return input.contains("todo");
    }

    /**
     * Checks if the provided input string contains the keyword "deadline".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "deadline"; false otherwise.
     */
    public boolean containDeadline(String input) {
        return input.contains("deadline");
    }

    /**
     * Checks if the provided input string contains the keyword "event".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "event"; false otherwise.
     */
    public boolean containEvent(String input) {
        return input.contains("event");
    }

    /**
     * Checks if the provided input string contains the keyword "delete".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "delete"; false otherwise.
     */
    public boolean containDelete(String input) {
        return input.contains("delete");
    }



    /**
     * Performs a marking or unmarking operation on a task based on the provided input string.
     * The last character of the input is used to determine the index of the task to be marked or unmarked.
     * If the input indicates an unmarking operation, the task is unmarked; otherwise, it is marked.
     *
     * @param input Input string that determines the task to be marked or unmarked.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void performMark(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        if (containUnmark(input)) {
            TaskList.unmark_task(list_no);
        } else {
            TaskList.mark_task(list_no);
        }
    }

    /**
     * Creates a new ToDoTask based on the input and adds it to the task list.
     * The 6th character of the input till the last character is the Task Name.
     * If an error occurs due to an invalid string index access or I/O issues, a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a ToDoTask as well as the name of the task.
     * @throws StringIndexOutOfBoundsException If the input string is too short to contain a valid task name.
     * @throws IOException If an I/O error occurs while adding the task.
     */
    public void performToDo(String input) {
        try {
            String name = input.substring(5);
            ToDos temp_todo = new ToDos(name);
            temp_todo.add_task(temp_todo);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            ui.empty_todo();
        }
    }

    /**
     * Creates a new Deadline task based on the input and adds it to the task list.
     * Input string contains the task name and a deadline date, separated by a "/".
     * The 10th character of the task name input till the last character is the actual task name.
     * The 3rd character of the deadline date till the last character is the actual date, which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a Deadline task.
     *              The format of the string should be "deadline <task name> /by <YYYY-MM-DD HHMM>".
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     * @throws IOException If an I/O error occurs while adding the task.
     */
    public void performDeadline(String input) {
        try {
            String[] deadline_arr = input.split("/");
            String name = deadline_arr[0].substring(9);
            String date = deadline_arr[1].substring(3);

            String[] tokens = date.split(" ");
            String[] dateTokens = tokens[0].split("-");
            String time = tokens[1];
            String year = dateTokens[0];
            String month = dateTokens[1];
            String day = dateTokens[2];
            LocalDateTime ldt = ds.createDate(year,month,day,time.substring(0,2),time.substring(2));

            Deadlines temp_deadline = new Deadlines(name, ldt);
            temp_deadline.add_task(temp_deadline);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            ui.empty_deadline();
        }
    }


    /**
     * Creates a new Event task based on the input and adds it to the task list.
     * The input string contains the event name, start time, and end time, separated by "/".
     * The 6th character of the event name input till the last character is the actual event name.
     * The 5th character of the start time input till the last character is the actual start time, which is then parsed into a LocalDateTime object.
     * The 3rd character of the end time input till the last character is the actual end time, which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message is displayed.
     *
     * @param input Input string containing the command to create an Event task.
     *              The format of the string should be "event <event name> /from <YYYY-MM-DD HHMM> /to <YYYY-MM-DD HHMM>".
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     * @throws IOException If an I/O error occurs while adding the task.
     */
    public void performEvent(String input) {
        try {
            String[] deadline_arr = input.split("/");
            String name = deadline_arr[0].substring(6);
            String start = deadline_arr[1].substring(5);
            String end = deadline_arr[2].substring(3);

            String[] full_date_token_start = start.split(" ");
            String[] date_token_start = full_date_token_start[0].split("-");

            System.out.println("Start Date: " + date_token_start[0] + " / " + date_token_start[1] + " / " + date_token_start[2]);

            LocalDateTime ldt_start = ds.createDate(date_token_start[0],date_token_start[1],date_token_start[2],full_date_token_start[1].substring(0,2),full_date_token_start[1].substring(2));

            String[] full_date_token_end = end.split(" ");
            String[] date_token_end = full_date_token_end[0].split("-");

            LocalDateTime ldt_end = ds.createDate(date_token_end[0],date_token_end[1],date_token_end[2],full_date_token_end[1].substring(0,2),full_date_token_end[1].substring(2));

            Events temp_event = new Events(name, ldt_start, ldt_end);
            temp_event.add_task(temp_event);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            ui.empty_event();
        }
    }

    /**
     * Deletes a task from the task list based on the input.
     * The last character of the input string is used to determine the task number to be deleted.
     *
     * @param input Input string that determines the task to be deleted.
     * @throws IOException If an I/O error occurs during the deletion operation.
     */
    public void performDelete(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        TaskList.delete_task(list_no);
    }
}
