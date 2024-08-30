package commands;

import java.io.IOException;
import java.time.LocalDateTime;

public class Events extends TaskList {
    LocalDateTime start;
    LocalDateTime end;
    public Events(String name, LocalDateTime start, LocalDateTime end) throws IOException {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    public void add_task(Events e) throws IOException {
        task_List_list.add(e);
//        super.update_saved_tasklist();
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][_] " + e.getName() + "(from: " + ds.format(this.start) + " to: " + ds.format(this.end) + ")");
        System.out.println("Now you have " + e.get_list_size() +" tasks in the list.");
        update_tasklist(e);
    }

    private void update_tasklist(Events e) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = task_List_list.size();
        StringBuilder information;
        if (e.getCurrent_status()==status.MARKED) {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + marked + " " + e.getName());
        } else {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + unmarked + " " + e.getName());
        }

        information.append(" (from: ").append(ds.format(e.getStart())).append(" to: ").append(ds.format(e.getEnd())).append(")");
        fs.write(String.valueOf(information));
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
