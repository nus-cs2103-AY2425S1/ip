package nerf.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nerf.error.InvalidDataException;
import nerf.io.Parser;

/**
 * Class to storing and manipulating tasks
 */
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
        loadTasklist(taskList);
    }

    public void loadTasklist(List<String> taskList) {
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
     * Prints current list of tasks.
     * 
     * @return list of tasks.
     */
    public String printList() {
        String res = "Here are the task(s) in your list:\n";
        for (int i = 0; i < listings.size(); i++) {
            res += String.format("%d. %s", i + 1, listings.get(i)) + "\n";
        }
        return res;
    }

    /**
     * Sets task completion on specified task.
     * 
     * @param input specific index of task.
     * @return Mark task confirmation.
     */
    public String markTask(String input) {
        String number = input.substring(4).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num - 1).setDone();
            return  """
                    Wow! Congrats on finishing another task!
                    I've marked this task as done:\n
                    """ + listings.get(num - 1);
        } catch (NumberFormatException e) {
            return "Oops! That does not seem like a number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oops! Please specific a number within the list.";
        }
    }

    /**
     * Unsets task completion on specified task.
     * 
     * @param input specific index of task.
     * @return unmark task confirmation.
     */
    public String unmarkTask(String input) {
        String number = input.substring(6).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num - 1).setUndone();
            return  """
                    Ok noted!
                    I've marked this task as undone:\n
                    """ + listings.get(num - 1);
        } catch (NumberFormatException e) {
            return "Oops! That does not seem like a number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oops! Please specific a number within the list.";
        }
    }

    /**
     * Filters task based on keyword.
     * 
     * @param input keyword to filter by.
     * @return list of filtered task.
     */
    public String findTasks(String input) {
        String keyword = input.substring(4).trim();
        List<Task> filteredList = new ArrayList<>();
        for (Task i : this.listings) {
            if (i.isKeywordMatched(keyword)) {
                filteredList.add(i);
            }
        }
        
        String res = "Here are the matching task(s) in your list:\n";
        for (int i = 0; i < filteredList.size(); i++) {
            res += String.format("%d. %s", i + 1, filteredList.get(i)) + "\n";
        }
        return res;
    }

    /**
     * Adds new task to list.
     * 
     * @param input Task object to be added.
     * @return task added confirmation.
     */
    private String addToList(Task input) {
        listings.add(input);
        return String.format("""
                             Understood. I've added the following task:
                                 %s
                             You now have %d task(s) in total.
                             """, input.toString(), listings.size());
        
    }

    /**
     * Converts given string to Todo object.
     * 
     * @param input string command.
     * @return task added confirmation.
     * @throws InvalidDataException if command is missing taskDesc parameter.
     */
    public String addTodo(String input) throws InvalidDataException {
        String taskDesc = input.substring(4).trim();
        if (taskDesc.equals("")) {
            throw new InvalidDataException();
        }
        return addToList(new ToDos(taskDesc));
    }

    /**
     * Converts given string to Deadline object.
     * 
     * @param input string command.
     * @return task added confirmation.
     * @throws InvalidDataException if command is missing taskDesc or duedate parameter.
     */
    public String addDeadline(String input) throws InvalidDataException {
        input = input.substring(8).trim();
        String[] parts = input.split("/by", 2);
        if (parts.length != 2) {
            return "Syntax: deadline <taskname> /by <datetime>";
        }
        String taskDesc = parts[0].trim();
        LocalDate deadline = Parser.parseStringToDate(parts[1].trim());
        if (taskDesc.equals("") || deadline == null) {
            throw new InvalidDataException();
        }
        return addToList(new Deadlines(taskDesc,deadline));
    }

    /**
     * Converts given string to Event object.
     * 
     * @param input string command.
     * @return task added confirmation.
     * @throws InvalidDataException if command is missing taskDesc or fromDate or toDate parameter.
     */
    public String addEvent(String input) throws InvalidDataException {
        input = input.substring(5).trim();
        String[] part1 = input.split("/from", 2);
        if (part1.length != 2) {
            return "Syntax: event <taskname> /from <datetime> /to <datetime>";
        }
        String[] part2 = part1[1].split("/to", 2);
        if (part2.length != 2) {
            return "Syntax: event <taskname> /from <datetime> /to <datetime>";
        } 
        String taskDesc = part1[0].trim();
        LocalDate from = Parser.parseStringToDate(part2[0].trim());
        LocalDate to = Parser.parseStringToDate(part2[1].trim());
        if (taskDesc.equals("") || from == null || to  == null) {
            throw new InvalidDataException();
        }
        return addToList(new Events(taskDesc, from, to));
    }

    /**
     * Deletes specific task.
     * 
     * @param input specific index of task.
     * @return task deletion confirmation.
     */
    public String deleteTask(String input) {
        String number = input.substring(6).trim();
        try {
            int num = Integer.parseInt(number);
            String taskDeleted = listings.get(num - 1).toString();
            listings.remove(num - 1);
            return String.format("""
                                 Noted, removing:
                                     %s
                                 You now have %d task(s) in total.""", taskDeleted, listings.size());
        } catch (NumberFormatException e) {
           return "Oops! That does not seem like a number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oops! Please specific a number within the list.";
        }
    }

    /**
     * Returns list of Task as strings ready to be saved to file.
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
