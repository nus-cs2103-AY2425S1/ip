package tecna;

import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Displays all the tasks in the task list
     */
    public void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size; ++i) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }
    }

    /**
     * Adds new item to the list of tasks
     * @param item extracted from the user input
     * @throws InvalidRequestException
     * @throws TodoWrongFormatException
     */
    public void addItem(String item) throws TodoWrongFormatException, InvalidRequestException {
       Task task = getTask(item);
       this.tasks.add(task);
       ++this.size;
        System.out.println("Sure! I've added this task:");
        System.out.println(task);
        System.out.println(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list." );
    }

    /**
     * Creates the appropriate type of task
     * based on the input
     * @param input including type of task and task description
     * @throws InvalidRequestException
     * @throws TodoWrongFormatException
     * @return the corresponding task with correct type
     */
    private Task getTask(String input) throws InvalidRequestException, TodoWrongFormatException {
        int boundary = input.indexOf(" ");
        String category;
        try {
            category = input.substring(0, boundary);
        } catch (StringIndexOutOfBoundsException e) {
            category = input;
        }
        if (category.equalsIgnoreCase("todo")) {
            if (boundary == -1) {
                throw new TodoWrongFormatException("todo description cannot be empty!");
            }
            String des = input.substring(boundary + 1);
            if (des.isBlank()) {
                throw new TodoWrongFormatException("todo description cannot be empty!");
            }
            return new ToDo(des);
        } else if (category.equalsIgnoreCase("deadline")) {
            String[] description = input.substring(boundary + 1).split("/by");
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return new Deadline(description[0].trim(), LocalDateTime.parse(description[1].trim(), pattern));

        } else if (category.equalsIgnoreCase("event")) {
            String[] description = input.substring(boundary + 1).split("/from | /to ");
           DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return new Event(description[0].trim(), LocalDateTime.parse(description[1].trim(), pattern), LocalDateTime.parse(description[2].trim(), pattern));
        } else {
            throw new InvalidRequestException();
        }
    }

    /**
     * Deletes the specified items.
     * @param index
     */
    public void deleteItem(int index) {
            String item = this.tasks.get(index).toString();
            this.tasks.remove(index);
            --this.size;
            System.out.println("Sure! I've deleted this task:");
            System.out.println(item);
            System.out.println(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list." );
    }

    /**
     * Marks the index th task as done.
     * @param index
     */
    public void mark(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the index th task.
     * @param index
     */
    public void unmark(int index) {
        this.tasks.get(index).unMarkAsDone();
    }
}


