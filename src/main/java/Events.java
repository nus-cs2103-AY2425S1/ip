public class Events extends Task{
    String start;
    String end;
    public Events(String name, String start, String end) {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    public void add_task(Events e) {
        task_list.add(e);
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + e.getName() + "(from: " + this.start + " to: " + this.end + ")");
        System.out.println("Now you have " + e.get_list_size() +" tasks in the list.");
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
