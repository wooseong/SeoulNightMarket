package seoulnightmarket.seoulnightmarket.etc;

import java.util.ArrayList;
import java.util.List;

import seoulnightmarket.seoulnightmarket.data.ProductListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class Singleton
{
    private String type;
    private String region;
    private boolean serverRequest = false;
    private ArrayList<String> storeNameList = new ArrayList<String>();
    private ArrayList<String> storeImageList = new ArrayList<String>();
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

    public void setServerRequest(Boolean serverRequest) { this.serverRequest = serverRequest; }

    public boolean getServerRequest() { return serverRequest; }

    public void initStoreList()
    {
        storeNameList.clear();
        storeImageList.clear();
    }

    public void addStoreList(String storeName, String imageSource)
    {
        storeNameList.add(storeName);
        storeImageList.add(imageSource);
    }

    public ArrayList<String> getStoreNameList() { return storeNameList; }

    public ArrayList<String> getStoreImageList() { return storeImageList; }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}