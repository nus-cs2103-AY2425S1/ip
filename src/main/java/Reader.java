import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Reader extends PrintWriter {
    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    public Reader(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    public String peekLine() {
        peekToken();
        return line;
    }

    public void skipLine() {
        st = null;
        token = null;
    }
}
