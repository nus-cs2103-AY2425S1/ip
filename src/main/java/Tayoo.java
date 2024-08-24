public class Tayoo {
    public static void main(String[] args) {
        String name = "Tayoo";

        //Introduce self
        printHoriLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        printHoriLine();


        //Exit programme
        System.out.println("Bye. Hope to see you again soon!\n");
        printHoriLine();
    }

    private static void printHoriLine(int length) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i<length;i++) {
            line.append("_");
        }

        System.out.println(line);
    }

    private static void printHoriLine() {
        System.out.println("_______________________________________");
    }
}
