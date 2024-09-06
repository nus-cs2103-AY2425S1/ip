package diomon;

import java.time.LocalDate;

public class Task {
    String description;
    public static final String COMPLETEICON = "X";
    public static final String INCOMPLETEICON = " ";
    public static final String TYPEICON = " ";
    boolean completed;
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE,
    }

    public Task(String task){
        this.description = task;
        this.completed = false;
    }
    public Task(boolean complete, String description) {
        this.completed = complete;
        this.description = description;

    }
    public static Task of(String task, TaskType type) {
        switch (type) {
            case DEADLINE:
                String[] taskArray = task.split("/", 2);
                if (taskArray.length == 2){
                    String[] by = taskArray[1].split(" ", 2);
                    if (by[0].equalsIgnoreCase("by") && by.length == 2) {
                        return new Deadline(taskArray[0], LocalDate.parse(by[1],Parser.DATEFORMATTER));
                    }
                }
                throw new RuntimeException();

            case EVENT:
                String[] taskArr = task.split("/", 3);
                if (taskArr.length == 3){
                    String[] from = taskArr[1].split(" ", 2);
                    String[] to = taskArr[2].split(" ", 2);
                    if (from[0].equalsIgnoreCase("from") && from.length == 2 && to[0].equalsIgnoreCase("to") && to.length == 2) {
                        return new Event(taskArr[0],
                                LocalDate.parse(from[1].replaceAll(" ",""), Parser.DATEFORMATTER),
                                LocalDate.parse(to[1].replaceAll(" ",""),Parser.DATEFORMATTER));
                    }
                }
                throw new RuntimeException();
            case TODO:
                return new Todo(task);
            default:
                return new Task(task);
        }

    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? COMPLETEICON : INCOMPLETEICON;
    }
    public String getTypeIcon() {
        return TYPEICON;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String toStorageString(){
        return String.format("%s|%s|%s", TYPEICON, getStatusIcon(),this.description);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task temp) {
            return temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}
