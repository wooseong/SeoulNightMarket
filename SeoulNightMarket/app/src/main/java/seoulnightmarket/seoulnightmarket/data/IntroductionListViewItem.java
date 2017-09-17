package seoulnightmarket.seoulnightmarket.data;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class IntroductionListViewItem {
    private String introductionTitle;
    private String introductionContent;

    public IntroductionListViewItem() {

    }

    public String getIntroductionTitle() {
        return this.introductionTitle;
    }

    public String getIntroductionContent() {
        return this.introductionContent;
    }

    public void setIntroductionTitle(String title) {
        introductionTitle = title;
    }

    public void setIntroductionContent(String content) {
        introductionContent = content;
    }
}