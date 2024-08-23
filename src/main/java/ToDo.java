
public class ToDo extends Task {

  
    

    public ToDo(String content, Boolean status) {
        super(content, status);

    }

    
  
    
    @Override
    public String toString() {
        return "[T]"+super.toString() ;
    }


}
