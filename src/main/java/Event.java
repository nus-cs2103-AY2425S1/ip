
public class Event extends Task {

    
    private String from;
    private String to;

    public Event(String content, Boolean status,String from,String to) {
        super(content, status);
        this.from = from;
        this.to = to;

    }

    
  
    
    @Override
    public String toString() {
        return "[E]"+super.toString()+ " (from: "+ from +" " + "to: "+ to +")" ;
    }


}
