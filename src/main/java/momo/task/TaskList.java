package momo.task;

import momo.MomoException;
import momo.Parser;
import momo.command.CommandType;
import momo.task.Deadline;
import momo.task.Event;
import momo.task.Task;
import momo.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();
    private Parser parser;

    public TaskList(String fileText) {
        try {
            populateTaskList(fileText);
        }
        catch (MomoException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCount() {
        return list.size();
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int index) { return list.get(index); }

    public void addTodo(String desc) {
        list.add(new Todo(desc, false));
    }

    public void addDeadline(String desc, LocalDate by) {
        list.add(new Deadline(desc, by, false));
    }

    public void addEvent(String desc, LocalDate from, LocalDate to) {
        list.add(new Event(desc, from, to, false));
    }

    public  void deleteTask(int index)  {

        list.remove(index);
    }

    public  void changeCompletion(int index, CommandType command)  {
        Task task = list.get(index);

        if (command == CommandType.MARK) {
            task.markComplete();
        }
        else {
            task.unmark();
        }
    }


    public void populateTaskList(String input) throws MomoException {
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
