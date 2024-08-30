package commands;

import java.io.IOException;
import java.time.LocalDateTime;

public class ToDos extends TaskList {
    public ToDos(String name) throws IOException {
        super(name, "T");
    }

    public void add_task(ToDos t) throws IOException {
        TaskList.task_List_list.add(t);
//        super.update_saved_tasklist();
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][_] " + t.getName());
        System.out.println("Now you have " + t.get_list_size() +" tasks in the list.");

        update_tasklist(t);
    }

    private void update_tasklist(ToDos t) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.task_List_list.size();
        StringBuilder information;
        if (t.getCurrent_status()== TaskList.status.MARKED) {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + marked + " " + t.getName());
        } else {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + unmarked + " " + t.getName());
        }

        TaskList.fs.write(String.valueOf(information));
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
