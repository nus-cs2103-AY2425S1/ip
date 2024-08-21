public class Item {

    private String name;
    private Boolean done;

    public Item(String newname) {
        this.name = newname;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.toString());
    }

    public void markAsUndone() {
        this.done = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + this.toString());
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String str;
        str = String.format("[%s] %s",this.getStatusIcon(), this.name);
        return str;
    }
}
