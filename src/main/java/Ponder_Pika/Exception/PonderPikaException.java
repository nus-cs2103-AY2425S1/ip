package Ponder_Pika.Exception;

public class PonderPikaException extends Exception{

    public PonderPikaException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "Oh pika pi (T_T) - " + this.getMessage();
    }
}
