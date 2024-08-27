package gray;

import java.io.*;

public class Utility {
    public static void serialise(File file, Serializable obj) throws IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutput s = new ObjectOutputStream(f);
        s.writeObject(obj);
        s.flush();
        s.close();
        s.close();
    }

    public static Object deserialise(File file) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(in);
        Object obj = s.readObject();
        s.close();
        in.close();
        return obj;
    }
}
