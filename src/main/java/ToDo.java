public class ToDo extends Task {
    public ToDo(boolean mark, String task) {
        super(mark, task);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format(
                "  %s", this));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                super.task_list.size()));
    }

    @Override
    public String toString() {
        String s;
        s = String.format("[T]%s", super.toString());
        return s;
    }

}
