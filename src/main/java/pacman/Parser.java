package pacman;

import java.time.format.DateTimeParseException;

import pacman.exception.PacmanException;
import pacman.exception.PacmanInvalidDateException;
import pacman.exception.PacmanInvalidFormatException;

/**
 * Implements execution of the command that is sent
 */
public class Parser {
    private static Ui ui;
    private static TaskList list;

    private static void addList(Task newTask) {
        ui.showResult("Got it. I've added this task:");
        ui.showResult("  " + newTask);
        list.addTask(newTask);
        ui.showResult("Now you have " + list.getSize() + " tasks in the list.");
    }

    private static void printList() {
        ui.showResult(list.toString());
    }

    private static void markTask(int index) {
        ui.showResult("Got it. I've marked this task done:");
        ui.showResult("  " + list.toggleTask(index - 1, true));
    }

    private static void unmarkTask(int index) {
        ui.showResult("Got it. I've marked this task as not done yet:");
        ui.showResult("  " + list.toggleTask(index - 1, false));
    }

    private static void addTodo(String task) {
        addList(new Todo(task));
    }

    private static void addDeadline(String task) throws PacmanException {
        try {
            String[] splitter = task.split("/", 2);
            String taskName = splitter[0];
            String by = splitter[1].split(" ", 2)[1];
            addList(new Deadline(taskName, by));
        } catch (DateTimeParseException e) {
            throw new PacmanInvalidDateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PacmanInvalidFormatException();
        }
    }

    private static void addEvent(String task) throws PacmanException {
        try {
            String[] splitter = task.split("/", 3);
            String taskName = splitter[0];
            String from = splitter[1].split(" ", 2)[1];
            String to = splitter[2].split(" ", 2)[1];
            from = from.substring(0, from.length() - 1);
            addList(new Event(taskName, from, to));
        } catch (DateTimeParseException e) {
            throw new PacmanInvalidDateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PacmanInvalidFormatException();
        }
    }

    private static void deleteTask(int index) {
        Task deletedTask = list.deleteTask(index - 1);
        ui.showResult("Noted. I've removed this task:");
        ui.showResult("  " + deletedTask);
        ui.showResult("Now you have " + list.getSize() + " tasks in the list.");
    }

    private static void findTask(String matcher) {
        ui.showResult(list.findTask(matcher));
    }

    /**
     * Executes the command
     * If the command add, delete, or mark the tasks, <code>execute</code> use <code>list</code>'s method
     * If the command return an output, <code>execute</code> use <code>ui</code>'s method
     * If the command ask to exit, return true
     *
     * @param command command that to be executed
     * @param list <code>TaskList</code> object that to be used to update tasks
     * @param ui <code>Ui</code> object that to be used to output the result
     * @return return true if the command ask to exit and false otherwise
     */
    public static boolean execute(String command, TaskList list, Ui ui) {
        Parser.ui = ui;
        Parser.list = list;
        String type = command.split(" ", 2)[0];
        switch (type) {
        case "bye", "b" -> {
            return true;
        }
        case "list", "l" -> ui.showResult(list.toString());
        case "mark", "m" -> {
            try {
                markTask(Integer.parseInt(command.split(" ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the index number :(");
            } catch (NumberFormatException e) {
                ui.showResult("I'm sorry, but it's invalid index :(");
            }
        }
        case "unmark", "u" -> {
            try {
                unmarkTask(Integer.parseInt(command.split(" ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the index number :(");
            } catch (NumberFormatException e) {
                ui.showResult("I'm sorry, but it's invalid index :(");
            }
        }
        case "todo", "T" -> {
            try {
                String task = command.split(" ", 2)[1];
                addTodo(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the task name :(");
            }
        }
        case "deadline", "D" -> {
            try {
                String task = command.split(" ", 2)[1];
                addDeadline(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the task name or the time :(");
            } catch (PacmanException e) {
                ui.showResult(e.getMessage());
            }
        }
        case "event", "E" -> {
            try {
                String task = command.split(" ", 2)[1];
                addEvent(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the task name or the time :(");
            } catch (PacmanException e) {
                ui.showResult(e.getMessage());
            }
        }
        case "delete", "d" -> {
            try {
                deleteTask(Integer.parseInt(command.split(" ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the index number :(");
            } catch (NumberFormatException e) {
                ui.showResult("I'm sorry, but it's invalid index :(");
            }
        }
        case "find", "f" -> {
            try {
                findTask(command.split(" ", 2)[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showResult("I'm sorry, but I can't find the task name that to be find :(");
            }
        }
        default -> ui.showResult("I'm sorry, but I can't understand what you ask :(");
        }
        return false;
    }
}
