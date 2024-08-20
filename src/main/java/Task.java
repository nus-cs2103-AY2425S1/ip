public class Task {
    protected String description;

    public Task(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public String type(){
        return " ";
    }


    @Override
    public String toString() {
        return " ["+this.type()+"][ ] " + description;
    }
}
