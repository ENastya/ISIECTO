package com.example.helper.Entity;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductEd extends AsyncTask<Void, Void, List <Product>> {
        Element table;
        List<Product> list;
        MyIp ip = new MyIp();
    //Тут храним значение заголовка сайта

        @Override
        protected List<Product> doInBackground(Void... params) {

            Document doc = null;//Здесь хранится будет разобранный html документ
            try {
                //Считываем заглавную страницу http://harrix.org
                doc = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/shopsProducts/List.xhtml").get();
            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }

            //Если всё считалось, что вытаскиваем из считанного html документа заголовок
            table = doc.select("table").get(0);
            Elements rows = table.select("tr");
            list = new ArrayList<Product>();
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Product product = new Product();
                product.setName(cols.get(3).text());
                product.setShop(cols.get(2).text());
                product.setPrice(Integer.valueOf(cols.get(1).text()));
                list.add(product);
            }

            return list;
        }

}
