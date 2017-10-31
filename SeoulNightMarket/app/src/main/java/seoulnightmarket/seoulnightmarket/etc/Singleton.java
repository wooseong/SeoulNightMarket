package seoulnightmarket.seoulnightmarket.etc;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.Activity.MainActivity;
import seoulnightmarket.seoulnightmarket.Activity.NumberTicketActivity;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class Singleton {
    private int nowClient;
    private int lastClient;
    private int waitCount;
    private String SMSReceiver;
    private String type;
    private String region;
    private String nowStore;
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
    private String nowStoreImage;
    private String course;
    private String nowCategory;
    private String nowStoreDetailImage;
    private String nowLoginID;
    private String nowSeller;
    private String nowCallNickName;
    private ArrayList<Integer> nowWaitList = new ArrayList<Integer>();
    private ArrayList<Integer> myWaitList = new ArrayList<Integer>();
    private ArrayList<Integer> starScoreList = new ArrayList<Integer>();
    private ArrayList<String> nickmameList = new ArrayList<String>();
    private ArrayList<String> dateList = new ArrayList<String>();
    private ArrayList<String> describeList = new ArrayList<String>();
    private ArrayList<String> waitStoreList = new ArrayList<String>();
    private ArrayList<String> storeNameList = new ArrayList<String>();
    private ArrayList<String> storeImageList = new ArrayList<String>();
    private ArrayList<String> storeCategoryList = new ArrayList<String>();
    private ArrayList<String> storeDetialImageList = new ArrayList<String>();
    private ArrayList<String> productNameList = new ArrayList<String>();
    private ArrayList<String> productImageList = new ArrayList<String>();
    private ArrayList<String> productPriceList = new ArrayList<String>();
    private ArrayList<String> performanceNameList = new ArrayList<String>();
    private ArrayList<String> performanceImageList = new ArrayList<String>();
    private ImageView storeImageView;
    private TextView storeTextView;
    private TextView waitTextView;
    private boolean nowLogin = false;
    private boolean duplicated = false;
    private boolean btnOrder = false;

    private static Singleton instance = null;

    public int getWaitCount() {
        return waitCount;
    }

    public int getNowClient() {
        return nowClient;
    }

    public int getLastClient() {
        return lastClient;
    }

    public String getNowCallNickName() {
        return nowCallNickName;
    }

    public String getSMSReceiver() {
        return SMSReceiver;
    }

    public String getNowSeller() {
        return nowSeller;
    }

    public String getNowLoginID() {
        return nowLoginID;
    }

    public String getNowCategory() {
        return nowCategory;
    }

    public String getNowStoreDetailImage() {
        return nowStoreDetailImage;
    }

    public String getNowStore() {
        return nowStore;
    }

    public String getMarketPlace() {
        return marketPlace;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public String getBusWay() {
        return busWay;
    }

    public String getSubwayWay() {
        return subwayWay;
    }

    public String getCarWay() {
        return carWay;
    }

    public String getLoadmapSource() {
        return loadmapSource;
    }

    public String getOutlineSource() {
        return outlineSource;
    }

    public String getFormSource() {
        return formSource;
    }

    public String getOutlineTitle() {
        return outlineTitle;
    }

    public String getOutlineSubtitle() {
        return outlineSubtitle;
    }

    public String getOutlineDescribe() {
        return outlineDescribe;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public String getFormSubtitle() {
        return formSubtitle;
    }

    public String getFormDescribe() {
        return formDescribe;
    }

    public String getType() {
        return type;
    }

    public String getRegion() {
        return region;
    }

    public String getNowStoreImage() {
        return nowStoreImage;
    }

    public String getCourse() {
        return course;
    }

    public ArrayList<Integer> getStarScoreList() {
        return starScoreList;
    }

    public ArrayList<String> getDescribeList() {
        return describeList;
    }

    public ArrayList<String> getDateList() {
        return dateList;
    }

    public ArrayList<String> getNicknameList() {
        return nickmameList;
    }

    public ArrayList<String> getStoreNameList() {
        return storeNameList;
    }

    public ArrayList<String> getStoreImageList() {
        return storeImageList;
    }

    public ArrayList<String> getProductNameList() {
        return productNameList;
    }

    public ArrayList<String> getProductImageList() {
        return productImageList;
    }

    public ArrayList<String> getProductPriceList() {
        return productPriceList;
    }

    public ArrayList<String> getPerformanceNameList() {
        return performanceNameList;
    }

    public ArrayList<String> getPerformanceImageList() {
        return performanceImageList;
    }

    public ArrayList<String> getStoreCategoryList() {
        return storeCategoryList;
    }

    public ArrayList<String> getStoreDetialImageList() {
        return storeDetialImageList;
    }

    public ArrayList<String> getWaitStoreList() {
        return waitStoreList;
    }

    public ArrayList<Integer> getNowWaitList() {
        return nowWaitList;
    }

    public ArrayList<Integer> getMyWaitList() {
        return myWaitList;
    }

    public boolean getDuplicated() {
        return duplicated;
    }

    public ImageView getStoreImageView() {
        return storeImageView;
    }

    public TextView getStoreTextView() {
        return storeTextView;
    }

    public TextView getWaitTextView() {
        return waitTextView;
    }

    public boolean getNowLogin() {
        return nowLogin;
    }

    public boolean getBtnOrder() {
        return btnOrder;
    }

    public void setNowCallNickName(String nowCallNickName) {
        this.nowCallNickName = nowCallNickName;
    }

    public void setBtnOrder(boolean order) {
        this.btnOrder = order;
    }

    public void setSMSReceiver(String SMSReceiver) {
        this.SMSReceiver = SMSReceiver;
    }

    public void setWaitCount(int waitCount) {
        this.waitCount = waitCount;
    }

    public void setNowClient(int nowClient) {
        this.nowClient = nowClient;
    }

    public void setLastClient(int lastClient) {
        this.lastClient = lastClient;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setNowSeller(String nowSeller) {
        this.nowSeller = nowSeller;
    }

    public void setNowStore(String nowStore) {
        this.nowStore = nowStore;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public void setBusWay(String busWay) {
        this.busWay = busWay;
    }

    public void setSubwayWay(String subwayWay) {
        this.subwayWay = subwayWay;
    }

    public void setCarWay(String carWay) {
        this.carWay = carWay;
    }

    public void setLoadmapSource(String loadmapSource) {
        this.loadmapSource = loadmapSource;
    }

    public void setOutlineSource(String outlineSource) {
        this.outlineSource = outlineSource;
    }

    public void setFormSource(String formSource) {
        this.formSource = formSource;
    }

    public void setOutlineTitle(String outlineTitle) {
        this.outlineTitle = outlineTitle;
    }

    public void setNowLoginID(String nowLoginID) {
        this.nowLoginID = nowLoginID;
    }

    public void setOutlineSubtitle(String outlineSubtitle) {
        this.outlineSubtitle = outlineSubtitle;
    }

    public void setOutlineDescribe(String outlineDescribe) {
        this.outlineDescribe = outlineDescribe;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public void setFormSubtitle(String formSubtitle) {
        this.formSubtitle = formSubtitle;
    }

    public void setFormDescribe(String formDescribe) {
        this.formDescribe = formDescribe;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setNowStoreImage(String nowStoreImage) {
        this.nowStoreImage = nowStoreImage;
    }

    public void setStoreImageView(ImageView storeImageVIew) {
        this.storeImageView = storeImageVIew;
    }

    public void setDuplicated(boolean duplicated) {
        this.duplicated = duplicated;
    }

    public void setStoreTextView(TextView storeTextVIew) {
        this.storeTextView = storeTextVIew;
    }

    public void setWaitTextView(TextView waitTextVIew) {
        this.waitTextView = waitTextVIew;
    }

    public void setNowCategory(String nowCategory) {
        this.nowCategory = nowCategory;
    }

    public void setNowStoreDetailImage(String nowStoreDetailImage) {
        this.nowStoreDetailImage = nowStoreDetailImage;
    }

    public void setNowLogin(boolean nowLogin) {
        this.nowLogin = nowLogin;
    }

    public void initReviewList() {
        starScoreList.clear();
        nickmameList.clear();
        dateList.clear();
        describeList.clear();
    }

    public void initWaitList() {
        nowWaitList.clear();
        myWaitList.clear();
        waitStoreList.clear();
        storeImageList.clear();
    }

    public void initHandmadeStoreList() {
        storeNameList.clear();
        storeImageList.clear();
        storeCategoryList.clear();
        storeDetialImageList.clear();
    }

    public void initFoodStoreList() {
        storeNameList.clear();
        storeImageList.clear();
    }

    public void initFoodList() {
        productNameList.clear();
        productImageList.clear();
        productPriceList.clear();
    }

    public void initProductList() {
        productNameList.clear();
        productPriceList.clear();
    }

    public void initPerformanceList() {
        performanceNameList.clear();
        performanceImageList.clear();
    }


    public void addWaitList(int my, String store, String imageSource) {
        myWaitList.add(my);
        waitStoreList.add(store);
        storeImageList.add(imageSource);
    }

    public void addNowWaitList(int now) {
        nowWaitList.add(now);
    }

    public void addFoodStoreList(String storeName, String imageSource) {
        storeNameList.add(storeName);
        storeImageList.add(imageSource);
    }

    public void addHandmadeStoreList(String storeName, String imageSource, String category, String detailImageSource) {
        storeNameList.add(storeName);
        storeImageList.add(imageSource);
        storeCategoryList.add(category);
        storeDetialImageList.add(detailImageSource);
    }

    public void addReviewList(int starScore, String nickname, String date, String describe) {
        starScoreList.add(starScore);
        nickmameList.add(nickname);
        dateList.add(date);
        describeList.add(describe);
    }

    public void addFoodList(String productName, String imageSource, String price) {
        productNameList.add(productName);
        productImageList.add(imageSource);
        productPriceList.add(price);
    }

    public void addProductList(String productName, String price) {
        productNameList.add(productName);
        productPriceList.add(price);
    }

    public void addPerformanceList(String performanceName, String imageSource) {
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