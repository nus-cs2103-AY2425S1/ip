package nimbus.exception;

/**
 * Exception for CheckCommand if user input is not in correct format
 */
public class WrongCheckInputException extends NimbusException {

  /**
   * Creates exception with message to show users the correct input for CheckCommand
   */
  public WrongCheckInputException() {
        super("Wrong Check Command Input Format, Follow DD/MM/YYYY \n"
                + "Eg: 2/12/2024");
    }
}
