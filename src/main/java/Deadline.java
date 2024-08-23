public class Deadline extends Task {

    String time;

    public Deadline(boolean mark, String task, String time) {
        super(mark, task);
        this.time = time;
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", this));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                super.task_list.size()));
    }

    @Override
    public String toString() {
        String s;
        s = String.format("[D]%s (by: %s)",
                super.toString(), this.time);
        return s;
    }

}
