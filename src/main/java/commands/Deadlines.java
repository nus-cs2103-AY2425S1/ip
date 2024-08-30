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
        taskLists.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][_] " + d.getName() + "(by: " + dateTimeSystem.format(date) + ")");
        System.out.println("Now you have " + d.get_list_size() +" tasks in the list.");
        update_tasklist(d);
    }

    private void update_tasklist(Deadlines d) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = taskLists.size();
        StringBuilder information;
        if (d.getCurrentStatus()== Status.MARKED) {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + marked + " " + d.getName());
        } else {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + unmarked + " " + d.getName());
        }

        information.append(" (by: ").append(dateTimeSystem.format(d.getDate())).append(")");

        storage.write(String.valueOf(information));
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
