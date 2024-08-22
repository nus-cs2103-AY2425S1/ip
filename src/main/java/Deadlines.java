public class Deadlines extends Task{
    String day ;
    public Deadlines(String name, String day) {
        super(name, "D");
        this.day = day;
    }

    public void add_task(Deadlines d) {
        task_list.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + d.getName() + "(by: " + this.day + ")");
        System.out.println("Now you have " + d.get_list_size() +" tasks in the list.");
    }

}
