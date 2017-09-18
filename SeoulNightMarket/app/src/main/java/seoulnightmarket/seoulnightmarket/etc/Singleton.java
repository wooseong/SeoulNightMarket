package seoulnightmarket.seoulnightmarket.etc;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class Singleton {
    private String type;
    private static Singleton instance = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}