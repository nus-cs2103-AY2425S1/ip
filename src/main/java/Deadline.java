public class Deadline extends Task{
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[D]%s (by %s )", temp, date);
    }
}
