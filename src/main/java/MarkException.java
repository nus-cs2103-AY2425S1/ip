public class MarkException extends MoniqueException{
    public MarkException(){super("Mark Exception: Item Number is not allowed");}
    @Override
    public void advice(){
        System.out.println("Mark-related Exception. You have tried to mark an item which does not exist.");
    }
}