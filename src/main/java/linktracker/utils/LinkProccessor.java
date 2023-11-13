package linktracker.utils;

import linktracker.model.WebPage;

import java.util.concurrent.Callable;

public class LinkProccessor implements Callable {
    WebPage web;

    public  LinkProccessor(WebPage web){
        this.web = web;
    }

    @Override
    public WebPage call() throws Exception {
        web.setLinks(LinkReader.getLinks(web.getUrl()));
        return web;
    }
}
