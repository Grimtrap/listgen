import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class ItemGenerator {

    public ItemGenerator() {

    }

    public void searchItem() throws Exception {

        Gson gson = new Gson();

        File file = new File("itemlist.txt");
        PrintWriter writer = new PrintWriter(file);

        String response = "";

        //code that gets a json and converts it into an object
        URL itemSearch = new URL("https://api.wynncraft.com/public_api.php?action=itemDB&category=all"); //wynnapi url
        URLConnection yc = itemSearch.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        inputLine = in.readLine();
        ItemList itemList = gson.fromJson(inputLine, ItemList.class);
        Item[] items = itemList.getItems();
        Item currentItem = null;

        for(int i = 0; i < items.length; i++) {

            if(!(items[i].getDisplayName() == null || items[i].getDisplayName().equals("null"))) {
                response += items[i].getDisplayName() + ", ";
            } else {
                response += items[i].getName() + ", ";
            }

        }

        file.createNewFile();
        writer.write(response);

        writer.close();

    }
}
