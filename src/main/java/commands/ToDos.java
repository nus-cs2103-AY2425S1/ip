package commands;

import java.io.IOException;
import java.time.LocalDateTime;

public class ToDos extends TaskList {
    public ToDos(String name) throws IOException {
        super(name, "T");
    }

    public void addTask(ToDos t) throws IOException {
        TaskList.taskLists.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][_] " + t.getName());
        System.out.println("Now you have " + t.get_list_size() +" tasks in the list.");

        updateTaskList(t);
    }

    private void updateTaskList(ToDos t) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.taskLists.size();
        StringBuilder information;
        if (t.getCurrentStatus()== Status.MARKED) {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + marked + " " + t.getName());
        } else {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + unmarked + " " + t.getName());
        }

        TaskList.storage.write(String.valueOf(information));
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    @Override
    public LocalDateTime getStart() {
        return null;
    }

    @Override
    public LocalDateTime getEnd() {
        return null;
    }
}
