public abstract class SentinelException extends Exception{

    public SentinelException(String s){
        super(s);
    }
}

class DeadlineException extends SentinelException{
    public DeadlineException(String s){
        super(s);
    }
}

class EventException extends SentinelException{
    public EventException(String s){
        super(s);
    }
}


