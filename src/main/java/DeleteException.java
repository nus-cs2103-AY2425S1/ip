public class DeleteException extends MoniqueException{
    public DeleteException(){super("Delete Exception: Item Number is not allowed");}
    @Override
    public void advice(){
        System.out.println("Delete-related Exception. You have tried to delete an item which does not exist.");
    }
}