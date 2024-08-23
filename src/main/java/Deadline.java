
public class Deadline extends Task {

    private String content;
    private Boolean status;
    private String by;

    public Deadline(String content, Boolean status,String by) {
        super(content, status);
        this.by = by;

    }

    
  
    
    @Override
    public String toString() {
        return "[D]"+super.toString()+ " (by: "+ by +")" ;
    }


}
