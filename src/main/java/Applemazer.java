public class Applemazer {
    public static void main(String[] args) {
        String spacer = "____________________________________________________________";
        String greeting = String.format("%s\nHello! I'm Applemazer.\nWhat can I do for you?\n%s", spacer, spacer);
        String farewell = String.format("Bye. Hope to see you again soon!\n%s", spacer);
        System.out.println(greeting);
        System.out.println(farewell);
    }
}
