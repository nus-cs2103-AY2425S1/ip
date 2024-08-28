public class Task {
    private String description;
    private boolean isMarked;
    public Task (String desctiption, boolean isMarked) {
        this.description = desctiption;
        this.isMarked = isMarked;
    }
    public void mark() {
        this.isMarked = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + this.toString());
    };
    public void unmark() {
        this.isMarked = false;
        System.out.println("Ok! I've unmarked this task:");
        System.out.println("    " + this.toString());
    };

    @Override
    public String toString() {
        return isMarked ? "[X] " + this.description :
                "[ ] " + this.description;
    }

}
