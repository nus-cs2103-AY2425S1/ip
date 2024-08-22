public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override 
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static void main(String[] args) {
        Deadline dl = new Deadline("Do your work", "10th August");
        System.out.print(dl);
    }

}