public class Bobby {
    public static void main(String[] args) {
        horizontalLine(35);
        System.out.println();
        System.out.println("Hello! I'm Bobby");
        System.out.println("What can I do for you?");
        bye();
    }

    // Make a horizontal line of x dashes
    public static void horizontalLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
    }

    public static void bye() {
        horizontalLine(35);
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine(35);
    }
}
