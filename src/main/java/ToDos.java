import java.time.LocalDateTime;

public class ToDos extends Task{
    public ToDos(String content) {

        super(content);
    }
    @Override
    public LocalDateTime endTime() {
        return LocalDateTime.MAX;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
