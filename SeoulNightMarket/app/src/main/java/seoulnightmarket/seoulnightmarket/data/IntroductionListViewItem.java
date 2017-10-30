package seoulnightmarket.seoulnightmarket.data;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class IntroductionListViewItem
{
    private String introductionHeader;
    private String introductionTitle;
    private String introductionContent;

    public IntroductionListViewItem() {

    }

    public String getIntroductionHeader() { return introductionHeader;}

    public String getIntroductionTitle() {
        return introductionTitle;
    }

    public String getIntroductionContent() {
        return this.introductionContent;
    }

    public void setIntroductionHeader(String header) { this.introductionHeader = header; }

    public void setIntroductionTitle(String title) {
        this.introductionTitle = title;
    }

    public void setIntroductionContent(String content) {
        this.introductionContent = content;
    }
}