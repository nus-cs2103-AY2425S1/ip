public class Ui {
    private final int lineLength = 50;
    private final String horizontalLine = "\t" + "-".repeat(this.lineLength);

    public void printHorizontalLine() {
        System.out.println(this.horizontalLine);
    }

    public void echo(String msg) {
        this.printHorizontalLine();
        System.out.println("\t" + msg);
        this.printHorizontalLine();
    }
}