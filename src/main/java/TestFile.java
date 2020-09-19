import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        System.out.println (System.getProperty ("user.dir"));
        File file = new File ("static/");
        System.out.println (file.exists ());
        if(!file.exists ())file.mkdirs ();
    }
}
