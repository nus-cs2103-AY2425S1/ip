public class MrTracker {

    public static void printLine() {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        String name = "Mr Tracker";
        MrTracker.printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        MrTracker.printLine();
        System.out.println("Bye. Hope to see you again soon! \n");
        MrTracker.printLine();
    }
}
