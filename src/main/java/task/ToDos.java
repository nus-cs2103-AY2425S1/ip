package task;

import java.io.IOException;
import java.time.LocalDateTime;

public class ToDos extends Task {
    public ToDos(String name) throws IOException {
        super(name, "T");
    }

    public String addTask(ToDos t) throws IOException {
        TaskList.addTasks(t);
        updateTaskList(t);
        return ui.addTodoMessage(t);
    }

    private void updateTaskList(ToDos t) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
//        System.out.println("====DEBUG==== Size of list: " + index);
//        for (int i = 0; i < TaskList.tasks.size(); i++) {
//            System.out.println("TESTING : " + TaskList.tasks.get(i).getName());
//        }
        StringBuilder information;
        if (t.getCurrentStatus()== Status.MARKED) {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + marked + " " + t.getName());
        } else {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + unmarked + " " + t.getName());
        }

        Task.storage.write(String.valueOf(information));
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
