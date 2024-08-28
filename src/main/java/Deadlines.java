import java.io.IOException;

public class Deadlines extends Task{
    String day ;
    public Deadlines(String name, String day) throws IOException {
        super(name, "D");
        this.day = day;
    }

    public void add_task(Deadlines d) throws IOException {
        task_list.add(d);
//        super.update_saved_tasklist();

        System.out.println("Got it. I've added this task:");
        System.out.println("[D][_] " + d.getName() + "(by: " + this.day + ")");
        System.out.println("Now you have " + d.get_list_size() +" tasks in the list.");
        update_tasklist(d);
    }

    private void update_tasklist(Deadlines d) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = task_list.size();
        StringBuilder information;
        if (d.getCurrent_status()==status.MARKED) {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + marked + " " + d.getName());
        } else {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + unmarked + " " + d.getName());
        }

        information.append(" (by: ").append(d.getDay()).append(")");

        fs.write(String.valueOf(information));
    }

    public String getDay() {
        return day;
    }

    @Override
    public String getStart() {
        return null;
    }

    @Override
    public String getEnd() {
        return null;
    }
}
