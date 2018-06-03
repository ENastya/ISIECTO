package com.example.helper.Entity;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopEd extends AsyncTask<Void, Void, List <Shop>> {
        Element table;
        List<Shop> list;
        MyIp ip = new MyIp();
    //Тут храним значение заголовка сайта

        @Override
        protected List<Shop> doInBackground(Void... params) {

            Document doc = null;//Здесь хранится будет разобранный html документ
            try {
                //Считываем заглавную страницу http://harrix.org
                doc = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/shops/List.xhtml").get();
            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }

            //Если всё считалось, что вытаскиваем из считанного html документа заголовок
            table = doc.select("table").get(0);
            Elements rows = table.select("tr");
            list = new ArrayList<Shop>();
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Shop shop = new Shop();
                shop.setName(cols.get(1).text());
                shop.setAdress(cols.get(2).text());
                shop.setUserId(Integer.valueOf(cols.get(3).text()));
                list.add(shop);
            }

            return list;
        }

}
