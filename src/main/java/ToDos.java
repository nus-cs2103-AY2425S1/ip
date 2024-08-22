public class ToDos extends Task{
    public ToDos(String name) {
        super(name, "T");
    }

    public void add_task(ToDos t) {
        task_list.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + t.getName());
        System.out.println("Now you have " + t.get_list_size() +" tasks in the list.");
    }
    public void added_task_message(Task t) {

    }

    @Override
    public String getDay() {
        return null;
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
