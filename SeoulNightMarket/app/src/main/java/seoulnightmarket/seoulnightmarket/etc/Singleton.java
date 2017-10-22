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
    private String marketPlace;
    private String marketAddress;
    private String busWay;
    private String subwayWay;
    private String carWay;
    private String loadmapSource;
    private String outlineSource;
    private String formSource;
    private String outlineTitle;
    private String outlineSubtitle;
    private String outlineDescribe;
    private String formTitle;
    private String formSubtitle;
    private String formDescribe;
    private boolean serverRequest = false;
    private ArrayList<String> storeNameList = new ArrayList<String>();
    private ArrayList<String> storeImageList = new ArrayList<String>();
    private ArrayList<String> performanceNameList = new ArrayList<String>();
    private ArrayList<String> performanceImageList = new ArrayList<String>();
    private static Singleton instance = null;

    public String getMarketPlace() { return marketPlace;}
    public String getMarketAddress() { return marketAddress;}
    public String getBusWay() { return busWay;}
    public String getSubwayWay() { return subwayWay;}
    public String getCarWay() { return carWay;}
    public String getLoadmapSource() { return loadmapSource;}
    public String getOutlineSource() { return outlineSource;}
    public String getFormSource() { return formSource;}
    public String getOutlineTitle() { return outlineTitle;}
    public String getOutlineSubtitle() { return outlineSubtitle;}
    public String getOutlineDescribe() { return outlineDescribe;}
    public String getFormTitle() { return formTitle;}
    public String getFormSubtitle() { return formSubtitle;}
    public String getFormDescribe() { return formDescribe;}
    public String getType() {
        return type;
    }
    public String getRegion() {
        return region;
    }
    public boolean getServerRequest() { return serverRequest; }
    public ArrayList<String> getStoreNameList() { return storeNameList; }
    public ArrayList<String> getStoreImageList() { return storeImageList; }
    public ArrayList<String> getPerformanceNameList() { return performanceNameList; }
    public ArrayList<String> getPerformanceImageList() { return performanceImageList; }


    public void setMarketPlace(String marketPlace) { this.marketPlace = marketPlace;}
    public void setMarketAddress(String marketAddress) { this.marketAddress = marketAddress;}
    public void setBusWay(String busWay) { this.busWay = busWay;}
    public void setSubwayWay(String subwayWay) { this.subwayWay = subwayWay;}
    public void setCarWay(String carWay) { this.carWay = carWay;}
    public void setLoadmapSource(String loadmapSource) { this.loadmapSource = loadmapSource;}
    public void setOutlineSource(String outlineSource) { this.outlineSource =  outlineSource;}
    public void setFormSource(String formSource) { this.formSource = formSource;}
    public void setOutlineTitle(String outlineTitle) { this.outlineTitle = outlineTitle;}
    public void setOutlineSubtitle(String outlineSubtitle) { this.outlineSubtitle = outlineSubtitle;}
    public void setOutlineDescribe(String outlineDescribe) { this.outlineDescribe = outlineDescribe;}
    public void setFormTitle(String formTitle) { this.formTitle = formTitle;}
    public void setFormSubtitle(String formSubtitle) { this.formSubtitle = formSubtitle;}
    public void setFormDescribe(String formDescribe) { this.formDescribe = formDescribe;}
    public void setType(String type) {
        this.type = type;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setServerRequest(Boolean serverRequest) { this.serverRequest = serverRequest; }

    public void initStoreList()
    {
        storeNameList.clear();
        storeImageList.clear();
    }

    public void initPerformanceList()
    {
        performanceNameList.clear();
        performanceImageList.clear();
    }

    public void addStoreList(String storeName, String imageSource)
    {
        storeNameList.add(storeName);
        storeImageList.add(imageSource);
    }

    public void addPerformanceList(String performanceName, String imageSource)
    {
        performanceNameList.add(performanceName);
        performanceImageList.add(imageSource);
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}