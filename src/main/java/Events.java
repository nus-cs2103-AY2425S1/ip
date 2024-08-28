import java.io.IOException;

public class Events extends Task{
    String start;
    String end;
    public Events(String name, String start, String end) throws IOException {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    public void add_task(Events e) throws IOException {
        task_list.add(e);
//        super.update_saved_tasklist();

        System.out.println("Got it. I've added this task:");
        System.out.println("[E][_] " + e.getName() + "(from: " + this.start + " to: " + this.end + ")");
        System.out.println("Now you have " + e.get_list_size() +" tasks in the list.");
        update_tasklist(e);
    }

    private void update_tasklist(Events e) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = task_list.size();
        StringBuilder information;
        if (e.getCurrent_status()==status.MARKED) {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + marked + " " + e.getName());
        } else {
            information = new StringBuilder(index + ". [" + e.getTag() + "]" + unmarked + " " + e.getName());
        }

        information.append(" (from: ").append(e.getStart()).append(" to: ").append(e.getEnd()).append(")");
        fs.write(String.valueOf(information));
    }

    @Override
    public String getDay() {
        return null;
    }

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public String getEnd() {
        return end;
    }
}
