public class Dudu {
    public static void main(String[] args) {
        String lineRule = "--------------------------------------------\n";
        String welcomeMessage = lineRule
                              + "Hello! I'm dudu\n"
                              + "What can I do for you?\n"
                              + lineRule;
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                              + lineRule;
        System.out.println(welcomeMessage + goodbyeMessage);
    }
}
