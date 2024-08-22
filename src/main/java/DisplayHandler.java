public class DisplayHandler {
    private int LINE_LENGTH = 50;

    public void output(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    public void output(String[] messages) {
        drawLine();
        for (String message : messages) {
            System.out.println(message);
        }
        drawLine();
    }

    public void drawLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }
}
