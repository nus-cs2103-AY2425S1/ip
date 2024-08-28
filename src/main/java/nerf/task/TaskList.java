package nerf.task;

/**
 * Class to storing and manipulating tasks
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nerf.error.InvalidDataException;
import nerf.io.Parser;

public class TaskList {
    private final List<Task> listings;

    /**
     * Default constructor if save file is unable to read.
     */

    public TaskList() {
        this.listings = new ArrayList<>();
    }
    
    /**
     * Constructor for loading tasks from save file into task objects.
     * 
     * @param taskList list of strings retrieved from file.
     */
    public TaskList(List<String> taskList) {
        this.listings = new ArrayList<>();
        for (String taskLine: taskList) {
            String[] task = taskLine.split("\\|");
            switch (task[0].trim()) {
            case "T" -> listings.add(new ToDos(task[2].trim(), task[1].trim().equals("1")));
            case "D" -> {
                LocalDate dueDate = Parser.parseStringToDate(task[3].trim());
                listings.add(new Deadlines(task[2].trim(), task[1].trim().equals("1"), dueDate));
            }
            case "E" -> {
                LocalDate fromDate = Parser.parseStringToDate(task[3].trim());
                LocalDate toDate = Parser.parseStringToDate(task[4].trim());
                listings.add(new Events(task[2].trim(), task[1].trim().equals("1"), fromDate, toDate));
            }
            default -> System.out.println("Save file seems to be corrupted.");
            }
        }
    }

    /**
     * Print current list of tasks.
     */
    public void printList() {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < listings.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, listings.get(i)));
        }
    }

    /**
     * Set task completion on specified task.
     * 
     * @param input specific index of task.
     */
    public void markTask(String input) {
        String number = input.substring(4).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num - 1).setDone();
            System.out.println("""
                               Wow! Congrats on finishing another task!
                               I've marked this task as done:
                               """ + listings.get(num - 1));
        } catch (NumberFormatException e) {
            System.out.println("Oops! That does not seem like a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    /**
     * Unset task completion on specified task.
     * 
     * @param input specific index of task.
     */
    public void unmarkTask(String input) {
        String number = input.substring(6).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num - 1).setUndone();
            System.out.println("""
                               Ok noted!
                               I've marked this task as undone:
                               """ + listings.get(num - 1));
        } catch (NumberFormatException e) {
            System.out.println("Oops! That does not seem like a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    /**
     * Filter and find task based on keyword.
     * 
     * @param input keyword to filter by.
     */
    public void findTasks(String input) {
        String keyword = input.substring(4).trim();
        List<Task> filteredList = new ArrayList<>();
        for (Task i : this.listings) {
            if (i.matchSearch(keyword)) {
                filteredList.add(i);
            }
        }
        
        System.out.println("Here are the matching task(s) in your list:");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, filteredList.get(i)));
        }
    }

    /**
     * Add new task to list.
     * 
     * @param input Task object to be added.
     */
    private void addToList(Task input) {
        listings.add(input);
        System.out.println("Understood. I've added the following task:");
        System.out.println("  " + input);
        
        System.out.println(String.format("You now have %d task(s) in total.", listings.size()));
    }

    /**
     * Convert given string to Todo object.
     * 
     * @param input string command.
     * @throws InvalidDataException if command is missing taskDesc parameter.
     */
    public void addTodo(String input) throws InvalidDataException {
        String taskDesc = input.substring(4).trim();
        if (taskDesc.equals("")) {
            throw new InvalidDataException();
        } else {
            addToList(new ToDos(taskDesc)); 
        }
    }

    /**
     * Convert given string to Deadline object.
     * 
     * @param input string command.
     * @throws InvalidDataException if command is missing taskDesc or duedate parameter.
     */
    public void addDeadline(String input) throws InvalidDataException {
        input = input.substring(8).trim();
        String[] parts = input.split("/by", 2);
        if (parts.length != 2) {
            System.out.println("Syntax: deadline <taskname> /by <datetime>");
        } else {
            String taskDesc = parts[0].trim();
            LocalDate deadline = Parser.parseStringToDate(parts[1].trim());
            if (taskDesc.equals("") || deadline == null) {
                throw new InvalidDataException();
            }
            addToList(new Deadlines(taskDesc,deadline));
        }
    }

    /**
     * Convert given string to Event object.
     * 
     * @param input string command.
     * @throws InvalidDataException if command is missing taskDesc or fromDate or toDate parameter.
     */
    public void addEvent(String input) throws InvalidDataException {
        input = input.substring(5).trim();
        String[] part1 = input.split("/from", 2);
        if (part1.length != 2) {
            System.out.println("Syntax: event <taskname> /from <datetime> /to <datetime>");
        }
        else {
            String[] part2 = part1[1].split("/to", 2);
            if (part2.length != 2) {
                System.out.println("Syntax: event <taskname> /from <datetime> /to <datetime>");
            } else {
                String taskDesc = part1[0].trim();
                LocalDate from = Parser.parseStringToDate(part2[0].trim());
                LocalDate to = Parser.parseStringToDate(part2[1].trim());
                if (taskDesc.equals("") || from == null || to  == null) {
                    throw new InvalidDataException();
                }
                addToList(new Events(taskDesc, from, to));
            }
        }
    }

    /**
     * Delete specific task.
     * 
     * @param input specific index of task.
     */
    public void deleteTask(String input) {
        String number = input.substring(6).trim();
        try {
            int num = Integer.parseInt(number);
            String taskDeleted = listings.get(num - 1).toString();
            listings.remove(num - 1);
            System.out.println(String.format("""
                              Noted, removing:
                                 %s
                              You now have %d task(s) in total.""", taskDeleted, listings.size()));
        } catch (NumberFormatException e) {
            System.out.println("Oops! That does not seem like a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    /**
     * Return list of Task as strings ready to be saved to file.
     * 
     * @return list of tasks as strings.
     */
    public List<String> getSaveable() {
        List<String> res = new ArrayList<>();
        for (Task i : listings) {
            res.add(i.getSaveFormat());
        }
        return res;
    }
}
