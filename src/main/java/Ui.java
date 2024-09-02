public class Ui {
    public Ui() { }

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    protected void displayException(Exception e) {
        printLongLine();
        System.out.println("\t" + e);
        printLongLine();
    }

    protected void displayText(String text) {
        printLongLine();
        System.out.println(text);
        printLongLine();
    }
}
