package david.Exceptions;

//Generic abstract exception related to all exceptions from the chatbot
public abstract class DavidException extends Exception{
    /**
     * Returns the error message of the exception
     * @return Custom String message of the exception
     */
    public String showErrorMessage(){
        return this.toString();
    };
}
