import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate byTime;

    public Deadline(String name, String description, LocalDate byTime) {
        super(name, description);
        this.byTime = byTime;
    }

    public LocalDate getByTime() {
        return byTime;
    }

    public void setByTime(LocalDate byTime) {
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                "\n\tBy: " + byTime.getDayOfMonth() + "/" + byTime.getMonth().toString().substring(0, 3) + "/" + byTime.getYear();
    }
}
