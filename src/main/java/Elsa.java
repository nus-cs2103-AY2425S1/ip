public class Elsa {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
        goodbye();
    }

    /**
     * This method adds a horizontal separator line consisting of underscores.
     */
    public static void addLine() {
        System.out.println("______________________________________");
    }

    /**
     * This method ends the conversation and says bye to the user.
     */
    public static void goodbye() {
        System.out.println("Bye! It was so nice chatting with you.\nSee you again soon!");
    }
}
