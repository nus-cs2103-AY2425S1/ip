package Winde;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Command {
    public abstract boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException;

    public void exit(History history, Reminder reminder, Ui ui) {
        ui.print("Bye. Hope to see you again soon!");
        history.save(reminder.getSchedule());
    }

    static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String message) {
            super(message);
        }
    }
    static class TooManyParametersException extends Exception {
        public TooManyParametersException(String message) {
            super(message);
        }
    }
}

class RemoveTask extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            Task deleted = reminder.remove(Integer.parseInt(command[1]) - 1);
            ui.print("Noted. I've removed this task:");
            ui.print("    " + deleted.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DELETING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

class AddEvent extends Command {

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /from ");
            if (order.length == 2) {
                String[] fillerName = order[1].split(" /to ");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime start = LocalDateTime.parse(fillerName[0], dateFormatter);
                LocalDateTime end = LocalDateTime.parse(fillerName[1], dateFormatter);
                Event e = new Event(order[0], start, end);
                reminder.addEvent(e);
                ui.print("Got it. I've added this task:");
                ui.print("    " + e.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
            } else {
                throw new EmptyDescriptionException("WHEN EVENT DATE!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M EVENT-ING!");
        }
        return true;
    }
}

class AddDeadline extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /by ");
            if (order.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(order[1], formatter);
                Deadline d = new Deadline(order[0], deadline);
                reminder.addDeadline(d);
                ui.print("Got it. I've added this task:");
                ui.print("    " + d.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
            } else {
                throw new EmptyDescriptionException("WHEN DEADLINE END!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DEADLINING!");
        }
        return true;
    }
}
class AddTodo extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            Todos td = new Todos(command[1]);
            reminder.addTodo(td);
            ui.print("Got it. I've added this task:");
            ui.print("    " + td.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M TODO-ING!");
        }
        return true;
    }
}

class AddUnmark extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            ui.print("OK, I've marked this task as not done yet:");
            reminder.unmark(Integer.parseInt(command[1]) - 1);
            ui.print("    " + reminder.getTask(Integer.parseInt(command[1]) - 1).toString());
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

class AddMark extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            ui.print("Nice! I've marked this task as done:");
            reminder.mark(Integer.parseInt(command[1]) - 1);
            ui.print("    " + reminder.getTask(Integer.parseInt(command[1]) - 1).toString());
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

class ListCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) {
        ui.print("Here are the tasks in your list:");
        if (reminder.size() == 0) {
            ui.print("Hurray you got nothing to do!");
        } else {
            for (int i = 1; i <= reminder.size(); i++) {
                ui.print(i + "." + " " + reminder.getTask(i - 1).toString());
            }
        }
        return true;
    }
}

class FindCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            ui.print("Here are the matching tasks in your list:");
            String keyWord = command[1];
            boolean taskNotFound = true;
            for (Task task : reminder.getSchedule()) {
                if (task.getAction().contains(keyWord)) {
                    ui.print(task.toString());
                    taskNotFound = false;
                }
            }
            if (taskNotFound) {
                ui.print("No words matches your query stoopid");
            }
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

class TaskDateCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(command[1], formatter);
            ui.print("These are the tasks you have for " + date.toString());
            ArrayList<Task> taskList = reminder.getTasksOnDate(date);
            if (taskList != null) {
                for (Task task : taskList) {
                    ui.print(task.toString());
                }
            } else {
                ui.print("Hurray! No tasks on: " + date.toString());
            }
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW THE DATE!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

class ByeCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) {
        return false;
    }
}

class ErrorCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) {
        ui.print("You inputted a wrong input stoopid");
        return false;
    }
}