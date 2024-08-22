
public abstract class Task {
    String name;
    boolean done;


    Task(String command){
        this.name = command;
        this.done = false;
    }


    public void markDone(){
        this.done = true;
    }

    public void unmark(){
        this.done = false;
    }
    @Override
    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();
        if (this.done){
            stringbuilder.append("[X] ");
        }else{
            stringbuilder.append("[ ] ");
        }
        stringbuilder.append(this.name);

        return stringbuilder.toString();
    }

    public abstract String toFileFormat();


}
