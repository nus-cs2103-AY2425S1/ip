public class VoidException extends Exception{
    public VoidException(String message) {
        super(message);
    }

    public void voidExceptionMessage() {
        System.out.printf("\t%s%n", "------------------------------------------------------------------");
        System.out.printf("\t%s%n", this.getMessage());
        System.out.printf("\t%s%n", "------------------------------------------------------------------");
    }
}
