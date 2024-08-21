public class Sam {
    public static void main(String[] args) {
        System.out.println(getHorizontalLine());
        System.out.println("Hello! I'm Sam\nWhat can I do for you?");
        System.out.println(getHorizontalLine());
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(getHorizontalLine());
    }

    private static String getHorizontalLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            line.append("-");
        }
        return line.toString();
    }
}
