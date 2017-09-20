package seoulnightmarket.seoulnightmarket.etc;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class Singleton {
    private String type;
    private String region;
    private static Singleton instance = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}