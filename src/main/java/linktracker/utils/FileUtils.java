package linktracker.utils;

import linktracker.model.WebPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<WebPage> loadPages(Path file) {
        //String[] lineParts = new String[2];
        String line;
        ArrayList<WebPage> webList = new ArrayList<>();
        try{
            FileReader reader = new FileReader(file.toFile());
            BufferedReader br = new BufferedReader(reader);
            while ((line = br.readLine()) != null){
                String[] lineParts = line.split(";");
                webList.add(new WebPage(lineParts[0], lineParts[1]));
            }
            MessageUtils.showMessage(webList.size() + " pages found", "File Loaded");
        }catch (Exception e){
            MessageUtils.showError(e.toString(), "Process error");
        }

        return webList;
    }
}
