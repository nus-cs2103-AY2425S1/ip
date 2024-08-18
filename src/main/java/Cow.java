public class Cow {
    public static void main(String[] args) {
        String line = "____________________________________________________________";

        String greetings = line + "\n"
                + " Hello! I'm COW\n"
                + " What can I do for you?\n"
                + line;

        String goodbye = " Bye. Hope to see you again soon!\n"
                + line;

        System.out.println(greetings);
        System.out.println(goodbye);
    }
}
