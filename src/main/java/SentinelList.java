import java.util.ArrayList;

public class SentinelList extends ArrayList<Task>{
    public SentinelList(){
    }
    public boolean sizeOne(){
        return size() == 1;
    }
    public String getListedString(int index){
        return get(index).listedString();
    }
    public void toggleMark(int index){
        if (get(index).isDone())
            get(index).setUndone();
        else get(index).setDone();

    }
    public void toggleMark(Task t){
        if (t.isDone())
            t.setUndone();
        else t.setDone();
    }
    public boolean taskIsDone(int index){
        return get(index).isDone();
    }
}
