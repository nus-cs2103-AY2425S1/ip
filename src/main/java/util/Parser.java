package util;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.TaskList;
import Tasks.Todo;
import exceptions.PrinceException;


// deals with making sense of the user command
// if dont do stretch goal, then all methods that handle user input goes in here
// if do stretch goal, methods are separated into their own commands
public class Parser {

    public static boolean parse(String input, TaskList tasksArray, Storage storage, Ui ui) {
        try {
            if (input.equals("bye")) {
                ui.showBye();
                return true;
            } else if (input.equals("list")) {
                ui.showTaskList(tasksArray);
            } else if (input.contains("unmark")) {
                unmark(input, tasksArray, storage);
                ui.showLine();
            } else if (input.contains("mark")) {
                mark(input, tasksArray, storage);
                ui.showLine();
            } else if (input.contains("delete")) {
                delete(input, tasksArray, storage);
                ui.showLine();
            } else if (input.equals("todo") || input.equals("deadline") ||
                    input.equals("event")) {
                throw new PrinceException("    Please describe your '" + input +
                        "' task in more detail!");
            } else if (input.contains("todo") || input.contains("deadline") ||
                    input.contains("event")) {
                if (input.contains("todo")) {
                    handleTodo(input, tasksArray, storage);
                }
                if (input.contains("deadline")) {
                    handleDeadline(input, tasksArray, storage);
                }
                if (input.contains("event")) {
                    handleEvent(input, tasksArray, storage);
                }
                ui.showLine();
            } else {
                throw new PrinceException("    Sorry, I am not sure what '" + input +
                        "' means. Please try again!");
            }
        } catch (PrinceException err) {
            System.out.println(err.toString());
            ui.showLine();
        }
        return false;
    }

    /*
     * Methods related to handling TODo tasks
     */

    // method to get description of the todo input
    private static String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    /*
     * Methods related to handling DEADLINE tasks
     */

    // method to get description of the deadline input
    private static String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    // method to get the deadline of deadline task input
    private static String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    /*
     * Methods related to handling EVENT tasks
     */

    // method to get description of the event input
    private static String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
    }

    // method to get from of the event input
    private static String getFrom(String input) {
        String[] arr = input.split("/from|/to");
        String from = arr[1].trim();
        return from;
    }

    // method to get to of the event input
    private static String getTo(String input) {
        String[] arr = input.split("/from|/to");
        String to = arr[2].trim();
        return to;
    }

    private static void handleTodo(String input, TaskList tasksArray, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getTodo(input);
        Todo todo = new Todo(desc);
        tasksArray.add(todo);
        System.out.println("      " + todo.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");

        storage.saveToFile(todo, tasksArray);
    }

    private static void handleDeadline(String input, TaskList tasksArray, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getDeadline(input);
        String by = getBy(input);
        Deadline deadlineTask = new Deadline(desc, by);
        tasksArray.add(deadlineTask);
        System.out.println("      " + deadlineTask.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");
        storage.saveToFile(deadlineTask, tasksArray);
    }

    private static void handleEvent(String input, TaskList tasksArray, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getEvent(input);
        String from = getFrom(input);
        String to = getTo(input);
        Event event = new Event(desc, from, to);
        tasksArray.add(event);
        System.out.println("      " + event.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");
        storage.saveToFile(event, tasksArray);
    }

    private static void unmark(String input, TaskList tasksArray, Storage storage) {
        // extra check to make sure the start of input is "unmark"
        String checkUnmark = input.substring(0, 6);
        if (checkUnmark.equals("unmark")) {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.markAsNotDone();
            storage.updateFile(input, tasksArray);
            System.out.println("      " + task.toString());
        }
    }

    private static void mark(String input, TaskList tasksArray, Storage storage) {
        // extra check to make sure the start of input is "mark"
        String checkMark = input.substring(0, 4);
        if (checkMark.equals("mark")) {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.markAsDone();
            storage.updateFile(input, tasksArray);
            System.out.println("      " + task.toString());
        }
    }

    private static void delete(String input, TaskList tasksArray, Storage storage) {

        // extra check to make sure the start of input is "delete"
        String checkDelete = input.substring(0, 6);
        if (checkDelete.equals("delete")) {
            // remove the task from storage.txt
            storage.deleteFromFile(input, tasksArray);
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.delete(); // prints "the noted i removed this task" string
            tasksArray.remove(index);
            System.out.println("      " + task.toString());
            System.out.println("    Now you have " + tasksArray.size() +
                    " tasks in the list.");
        }
    }

    /*
     * Methods related to UNMARK, MARK and DELETE
     */

    // method to get the integer when inputting unmark, mark or delete
    private static int getIndex(String input) {
        if (input.contains("unmark")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("mark")) {
            // get character value of index in input
            String indexAsString = input.substring(5);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("delete")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else {
            // should not reach here
            return -1;
        }
    }

}
