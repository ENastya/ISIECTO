package com.example.helper.Entity;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LogIn extends AsyncTask <Object, Object, User>{
        Element table;
        Shop shop;
        MyIp ip = new MyIp();


        @Override
        protected User doInBackground(Object... params) {
            String resultS;
            Document doc = null;//Здесь хранится будет разобранный html документ
            try {
                Connection.Response con = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/users/LogIn.xhtml")
                        .method(Connection.Method.GET)
                        .execute();
                doc = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/users/LogIn.xhtml").cookies(con.cookies()).get();
                Element form = doc.selectFirst("form");
                Elements rows = form.select("input");
                resultS = rows.get(rows.size() - 1).attr("value");
                User user = (User) params[0];
                doc = Jsoup.connect("http://"+ip.getIp()+":8080/HelperServ/faces/users/LogIn.xhtml")
                        .cookies(con.cookies())
                        .data("j_idt12", "j_idt12")
                        .data("j_idt12:email", user.getName())
                        .data("j_idt12:j_idt16", "j_idt12:j_idt16")
                        .data("j_idt12:password", user.getPass())
                        .data("javax.faces.ViewState", resultS)
                        .post();
                table = doc.select("table").get(0);
                rows = table.select("tr");
                Elements cols = rows.select("td");
                if (cols.size() == 6){
                user = new User();
                user.setId(Integer.valueOf(cols.get(1).select("span").get(0).text()));
                user.setName(String.valueOf(cols.get(3).select("span").get(0).text()));
                user.setPass(String.valueOf(cols.get(5).select("span").get(0).text()));
                return user;
                }

            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }
            return null;
        }
}
