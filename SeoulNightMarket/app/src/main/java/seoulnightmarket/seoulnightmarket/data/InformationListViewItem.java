package seoulnightmarket.seoulnightmarket.data;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class InformationListViewItem {
    private String informationTurn;
    private String informationDate;
    private String informationRegion;

    public InformationListViewItem() {

    }

    public String getTurn() {
        return this.informationTurn;
    }

    public String getDate() {
        return this.informationDate;
    }

    public String getRegion() {
        return this.informationRegion;
    }

    public void setTurn(String turn) {
        informationTurn = turn;
    }

    public void setDate(String date) {
        informationDate = date;
    }

    public void setRegion(String region) {
        informationRegion = region;
    }
}