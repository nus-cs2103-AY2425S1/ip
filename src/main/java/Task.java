public class Task {
    private String description;
    private Boolean marked;

    public Task(String desc, Boolean mark) {
        this.description = desc;
        this.marked = mark;
    }

    public void mark() {
        this.marked = true;
        System.out.println("Slay! marked it as done! \n"
                + this.printTask());
    }
    public void unMark() {
        this.marked = false;
        System.out.println("OK, I've marked it as not done yet \n"
                + this.printTask());
    }
    public String printTask() {
        String mark = marked ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }
}
