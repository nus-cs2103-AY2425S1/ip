public class UnknownCommandException extends MoniqueException{

    public UnknownCommandException(){
        super("Unknown Command Exception");
    }
    public void advice() {
        System.out.println("You have entered an unknown command");
        System.out.println("Please find out available commands by using '/commands'");
    }
}
