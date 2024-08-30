package bill;

/**
 * The BillException class extends the Exception class and  allows for custom exceptions specific to the Bill class.
 */
public class BillException extends Exception{
    public BillException(String str) {
        super(str);
    }
}
