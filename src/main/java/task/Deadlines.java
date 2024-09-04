package task;

import java.io.IOException;
import java.time.LocalDateTime;

public class Deadlines extends Task {
    LocalDateTime date;

    public Deadlines(String name, LocalDateTime date) throws IOException {
        super(name, "D");
        this.date = date;
    }

    public String addTask(Deadlines d) throws IOException {
        TaskList.addTasks(d);
        updateTasklist(d);

        return ui.addDeadlineMessage(d, date);
    }

    private void updateTasklist(Deadlines d) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
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
