package momo.task;

import momo.exception.MomoException;
import momo.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the list of Task objects stored in an ArrayList and provides
 * range of functionalities for all task objects within the list
 */
public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();
    private Parser parser;

    public TaskList(String fileText) {
        try {
            populateTaskList(fileText);
        } catch (MomoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for testing purposes
     */
    protected TaskList() {

    }

    /**
     * Returns the number of tasks in the ArrayList
     *
     * @return The int number of tasks in the ArrayList
     */
    public int getCount() {
        return list.size();
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int index) {

        return list.get(index);
    }


    /**
     * Deletes specified task via index from the ArrayList
     *
     * @param index Index of task to be deleted
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Populates the TaskList with all  the task strings inside the file
     * when the file is first loaded when the user boots up the program
     *
     * @param input Refers to the string containing all tasks from the storage file
     * @throws MomoException thrown when file is corrupted
     */
    public void populateTaskList(String input) throws MomoException {

        if (input.isEmpty()) {
            return;
        }

        String[] taskStrings = input.split("\n");
        for (String taskString : taskStrings) {
            String[] inputs = taskString.split("\\|");

            // For deadline and events assumption is that file will always be correctly formatted
            switch (inputs[0]) {
                case "T" -> {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Todo(inputs[2], false));

                    } else {
                        list.add(new Todo(inputs[2], true));
                    }
                }
                case "D" -> {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Deadline(inputs[2], parser.parseDate(inputs[3]), false));
                    } else {
                        list.add(new Deadline(inputs[2], parser.parseDate(inputs[3]), true));
                    }
                }
                case "E" -> {
                    if (Objects.equals(inputs[1], "1")) {
                        list.add(new Event(inputs[2], parser.parseDate(inputs[3]), parser.parseDate(inputs[4]), false));
                    } else {
                        list.add(new Event(inputs[2], parser.parseDate(inputs[3]), parser.parseDate(inputs[4]), true));
                    }
                }
                default -> throw new MomoException("Invalid file formatting....");
            }


        }
    }


}
