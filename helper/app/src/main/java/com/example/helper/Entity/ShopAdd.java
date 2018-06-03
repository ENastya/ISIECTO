package com.example.helper.Entity;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ShopAdd extends AsyncTask <Object, Object, Void>{
        Element table;
        Shop shop;
        MyIp ip = new MyIp();


        @Override
        protected Void doInBackground(Object... params) {
            String resultS;
            Document doc = null;//Здесь хранится будет разобранный html документ
            try {
                Connection.Response con = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/shops/Create.xhtml")
                        .method(Connection.Method.GET)
                        .execute();
                doc = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/shops/Create.xhtml").cookies(con.cookies()).get();
                Element form = doc.selectFirst("form");
                Elements rows = form.select("input");
                resultS = rows.get(rows.size() - 1).attr("value");
                shop = (Shop) params[0];
                Connection.Response con2 = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/shops/Create.xhtml")
                        .cookies(con.cookies())
                        .data("j_idt12", "j_idt12")
                        .data("j_idt12:id", "1111")
                        .data("j_idt12:j_idt19", "j_idt12:j_idt19")
                        .data("j_idt12:name", shop.getName())
                        .data("j_idt12:place", shop.getAdress())
                        .data("j_idt12:userId",String.valueOf(shop.getUserId()))
                        .data("javax.faces.ViewState", resultS)
                        .method(Connection.Method.POST)
                        .execute();

            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }
            return null;
        }
}
