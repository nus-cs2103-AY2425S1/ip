package sigma;
// deals with interactions with user
public class Ui {

    // just use default constructor

    // welcome message
    public String welcome() {
        return "Hello! I'm Sigma \nWhat can I do for you? \n";
    }


    // goodbye message
//    public void goodbye() {
//        System.out.println("leaving so soon? goodbye!");
//    }
    // don't recognise command message
    public void dontRecognise() {
        System.out.println( "erm, what the sigma? i don't recognise that command.");
    }

}
