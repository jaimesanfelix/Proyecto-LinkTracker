package linktracker.model;

import java.util.List;

public class WebPage {
    String webName;
    String url;
    List<String> links;

    public WebPage(String webName, String url){
        this.webName = webName;
        this.url = url;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
