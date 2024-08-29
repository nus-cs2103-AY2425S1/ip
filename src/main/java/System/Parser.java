package System;

import Commands.Deadlines;
import Commands.Events;
import Commands.TaskList;
import Commands.ToDos;
import javafx.concurrent.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class Parser {
    Ui ui = new Ui();
    DateTimeSystem ds = new DateTimeSystem();

    public boolean containBye(String input) {
        return input.contains(("bye"));
    }

    public boolean containList(String input) {
        return input.equalsIgnoreCase("list");
    }

    public boolean containMark(String input) {
        return input.contains("mark");
    }

    public boolean containUnmark(String input) {
        return input.contains("unmark");
    }

    public boolean containFind(String input) {
        return input.contains("find");
    }

    public void performFind(String input) throws FileNotFoundException {
        String name = input.substring(5);
        TaskList.findTask(name);
    }

    public void performListTasks() throws FileNotFoundException {
        TaskList.list_task();
    }

    public void performMark(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        if (containUnmark(input)) {
            TaskList.unmark_task(list_no);
        } else {
            TaskList.mark_task(list_no);
        }
    }

    public boolean containToDo(String input) {
        return input.contains("todo");
    }

    public void performToDo(String input) {
        try {
            String name = input.substring(5);
            ToDos temp_todo = new ToDos(name);
            temp_todo.add_task(temp_todo);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            ui.empty_todo();
        }
    }

    public boolean containDeadline(String input) {
        return input.contains("deadline");
    }

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

    public boolean containEvent(String input) {
        return input.contains("event");
    }

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

    public boolean containDelete(String input) {
        return input.contains("delete");
    }

    public void performDelete(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        TaskList.delete_task(list_no);
    }
}
