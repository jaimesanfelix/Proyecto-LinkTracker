package linktracker;

import linktracker.utils.LinkProccessor;
import linktracker.utils.LinkReader;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import linktracker.model.WebPage;
import linktracker.utils.FileUtils;
import linktracker.utils.MessageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class FXMLMainViewController {

    public MenuItem mi_loadFile;
    public ListView lv_name;
    public ListView lv_url;
    public Text t_pages;
    public Text t_processed;
    public Text t_links;
    public MenuItem mi_start;
    public MenuItem mi_clear;
    private ArrayList<WebPage> webList = new ArrayList<>();

    private ScheduledService<Boolean> schedServ;
    private ThreadPoolExecutor executor;

    private ArrayList<Future<WebPage>> result = new ArrayList<>();
    int processed = 0;
    int links = 0;
    public void initialize(){
        schedServ = new ScheduledService<Boolean>() {
            @Override
            protected Task<Boolean> createTask() {

                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {

                        for(Future<WebPage> res:result){
                            if (res.isDone()){
                                processed++;
                                links += res.get().getLinks().size();
                            }
                        }
                        t_processed.setText(String.valueOf(processed));
                        t_links.setText(String.valueOf(links));
                        return executor.isTerminated();
                    }
                };
            }
        };

        schedServ.setDelay(Duration.millis(100));
        schedServ.setPeriod(Duration.millis(100));
        schedServ.setOnSucceeded(e -> {
            onSucceeded();
        });


    }

    public void onStart(ActionEvent actionEvent){

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());

        for(int i = 0; i < webList.size(); i++)
        {
            LinkProccessor proccessor = new LinkProccessor(webList.get(i));
            result.add(executor.submit(proccessor));
        }

        executor.shutdown();
        schedServ.restart();
    }

    public void onSucceeded(){
        System.out.println("Finalizado");
        schedServ.cancel();
    }

    public void onLoadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        webList = (ArrayList<WebPage>) FileUtils.loadPages(selectedFile.toPath());

        for (WebPage web:webList) {
            lv_name.getItems().add(web.getWebName());
        }

        for(WebPage url:webList){
            lv_url.getItems().add(url.getUrl());
        }
        t_pages.setText(String.valueOf(webList.size()));
    }


    public void onClear(ActionEvent actionEvent) {

        schedServ.reset();
    }
}
