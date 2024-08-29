package ui;

public class Ui {
    public static void printText(String text) {
        String textToPrint = "____________________________________________________________\n"
                + text
                + "____________________________________________________________\n";

        System.out.println(textToPrint);
    }

    public static void printLine() {
        String line = "____________________________________________________________\n";
        System.out.println(line);
    }
}
