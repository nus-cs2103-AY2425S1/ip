public class Carly {

    public static void chat(){
        String welcomeMsg = "Hello! I'm Carly\nWhat can I do for you?\n";
        String exitMsg = "Bye. Hope to see you again soon!";
        String total = welcomeMsg + exitMsg;
        System.out.println(total);
    }
    public static void main(String[] args) {
        String logo = " ,-----.              ,--.          \n"
                    + "'  .--./,--,--.,--.--.|  |,--. ,--. \n"
                    + "|  |   ' ,-.  ||  .--'|  | \\  '  / \n"
                    + "'  '--'\\ '-'  ||  |   |  |  \\   ' \n"
                    + " `-----'`--`--'`--'   `--'.-'  /    \n"
                    + "                          `---'  ";

        System.out.println("Hello from\n" + logo);
        Carly.chat();
    }
}
