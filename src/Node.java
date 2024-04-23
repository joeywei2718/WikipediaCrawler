
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Node {

    public String selfURL;

    public String selfTitle;

    public String firstURL;

    public String firstTitle;



    public Node(String url) {

        this.selfURL = url;
        this.searchLink();

    }

    public String getSelfURL() {return this.selfURL;}
    public String getSelfTitle() {return this.selfTitle;}
    public String getFirstURL() {return this.firstURL;}
    public String getFirstTitle() {return this.firstTitle;}

    public void searchLink() {

        Document doc = null;

        try {
            doc = Jsoup.connect(this.selfURL).get();
        }

        catch (IOException e) {
            System.out.println("Not a valid link");
            e.printStackTrace();
        }

        assert doc != null;


        try {
            String title = doc.selectFirst(".mw-page-title-main").text();
            this.selfTitle = title;
        }

        catch (NullPointerException e) {

            String title = doc.selectFirst("#firstHeading").text();
            this.selfTitle = title;
        }


        Element body = doc.selectFirst("div.mw-body-content");
        assert body != null;
        Elements links = body.select("a");


        for (Element link : links) {
            Element parentTag = link.parent();
            if (parentTag.tagName().equals("p") &&
                    !parentTag.parent().tagName().equals("td") &&
                    !link.attr("title").equals("Help:Pronunciation respelling key") &&
                    !link.attr("title").contains("language")
            ) {
                String text = link.text();
                String url2 = link.absUrl("href");

                this.firstURL = url2;
                this.firstTitle = text;
                return;

            }
        }

    }

}


