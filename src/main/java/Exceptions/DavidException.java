package Exceptions;

//Generic abstract exception related to all exceptions from the chatbot
public abstract class DavidException extends Exception{
    public String showErrorMessage(){
        return this.toString();
    };
}
