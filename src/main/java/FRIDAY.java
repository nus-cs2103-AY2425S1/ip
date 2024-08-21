public class FRIDAY {
    public static void main(String[] args) {
        String divider = "";
        for (int i = 0; i < 40; i++) {
            divider += "-";
        }
        divider += "\n";

        String greeting = "Hello! I'm FRIDAY\nWhat can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";

        System.out.println(divider + greeting + "\n" + divider + exitMessage + "\n" + divider);
    }
}
