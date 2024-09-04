package task;

import java.io.IOException;
import java.time.LocalDateTime;

public class Events extends Task {
    LocalDateTime start;
    LocalDateTime end;

    public Events(String name, LocalDateTime start, LocalDateTime end) throws IOException {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    public String addTask(Events e) throws IOException {
        TaskList.addTasks(e);
        updateTasklist(e);
        return ui.addEventMessage(e, this.start, this.end);
    }

    private void updateTasklist(Events e) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
        StringBuilder information;
        if (e.getCurrentStatus() == Status.MARKED) {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + marked + " " + e.getName());
        } else {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + unmarked + " " + e.getName());
        }

        information.append(" (from: ").append(dateTimeSystem.format(e.getStart())).append(" to: ").append(dateTimeSystem.format(e.getEnd())).append(")");
        storage.write(String.valueOf(information));
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
