public class Ui {
    private String divider, tab;

    public Ui(String divider, String tab) {
        this.divider = divider;
        this.tab = tab;
    }

    public void printInDivider(String[] cont) {
        System.out.print(this.divider);
        for (String s : cont) {
            System.out.print(this.tab + s + "\n");
        }
        System.out.print(this.divider);
    }

    public void printTaskAddedWithDivider(String type, int size, Task task) {
        String[] toPrint = {"Got it. I've added this task:", "  [" + type + "][ ] " + task,
                "Now you have " + size + " tasks in the list."};
        printInDivider(toPrint);
    }

    public void printError(BlitzException err) {
        String[] toPrint = {err.toString()};
        printInDivider(toPrint);
    }
}
