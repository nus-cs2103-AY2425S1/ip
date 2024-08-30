package commands;

import java.io.IOException;
import java.time.LocalDateTime;

public class Deadlines extends TaskList {
    LocalDateTime date;

    public Deadlines(String name, LocalDateTime date) throws IOException {
        super(name, "D");
        this.date = date;
    }

    public void add_task(Deadlines d) throws IOException {
        task_List_list.add(d);
//        super.update_saved_tasklist();
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][_] " + d.getName() + "(by: " + ds.format(date) + ")");
        System.out.println("Now you have " + d.get_list_size() +" tasks in the list.");
        update_tasklist(d);
    }

    private void update_tasklist(Deadlines d) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = task_List_list.size();
        StringBuilder information;
        if (d.getCurrent_status()==status.MARKED) {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + marked + " " + d.getName());
        } else {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + unmarked + " " + d.getName());
        }

        information.append(" (by: ").append(ds.format(d.getDate())).append(")");

        fs.write(String.valueOf(information));
    }

    public LocalDateTime getDate() {
        return date;
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
