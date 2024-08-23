public class Deadline extends Task{
    private String end;

    public Deadline(String desc, String end){
        super(desc, "D");
        this.end = end;
    }

    @Override
    public String toString(){
        return super.toString() + "(by: " + this.end + ")";
    }
}
