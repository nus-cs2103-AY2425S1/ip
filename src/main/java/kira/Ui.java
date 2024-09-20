package kira;

public class Ui {
    private final String line = "____________________________________________________________\n";
    public Ui() {}

    public void showLoadingError() {
        System.out.println("  ____   ____  _____  \n" +
                " / __ \\ / __ \\|  __ \\ \n" +
                "| |  | | |  | | |__) | \n" +
                "| |  | | |  | |  ___/  \n" +
                "| |__| | |__| | |    \n" +
                " \\____/ \\____/|_|  \n");
    }

    public String addLines(String response) {
        return line + response + line;
    }

}
