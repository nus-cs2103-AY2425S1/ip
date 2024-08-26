package mendel.metacognition;

public class LeaveCommand extends Command{
    public String speak() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
