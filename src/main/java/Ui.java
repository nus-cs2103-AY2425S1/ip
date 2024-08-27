public class Ui {

    public void greet() {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");
    }

    public void exit() {
        System.out.println("See you next time! Remember to get a waffle!");
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

}
