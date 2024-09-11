package cypherchatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task implements Comparable<Deadline>{
    private LocalDateTime deadline;
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc,2);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = this.completed ? "[D][X] " : "[D][ ] ";
        str += String.format("%s (by: %s)", this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        return str;
    }

    @Override
    public String toStringInFile() {
        int val = this.completed ? 1 : 0;
        return String.format("D|%d|%s|%s", val, this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() == this.getClass()) {
            return Objects.equals(this.description, ((Deadline) o).description)
                    && this.deadline == ((Deadline) o).deadline;
        }
        return false;
    }

    @Override
    public int compareTo(Deadline compareDeadline) {
        return this.deadline.compareTo(compareDeadline.deadline);
    }
}
