package com.saingroceris.retailwebscraper;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Webscraperutil class is holding  logic to extract groceries product
 * information(title, size, unit price and product description from given URL
 * and return JSON object to console
 */
public class Webscraperutil {

    public Webscraperutil() {}

    public GroceriesInformation getProductInformations(String argUrl) {

        String title = "";
        float size = 0.0f;
        float unitPrice = 0.0f;
        String description = "";
        
        try {
            Document doc = Jsoup.connect(argUrl).get();
            Element v_element = doc.select("div.productTitleDescriptionContainer").first();
            if (v_element == null) {
                return null;
            } else {
                Element titleElement = v_element.getElementsByTag("h1").first();
                title = titleElement.text();
                size = doc.toString().length() / 1024;
            }
            v_element = doc.select("p.pricePerUnit").first();
            if (v_element == null) {
                return null;
            } else {
                String v_elemeng_text = v_element.text();
                v_elemeng_text = v_elemeng_text.replace("/unit", "");
                v_elemeng_text = v_elemeng_text.replace("£", "");
                float v_unit = Float.parseFloat(v_elemeng_text);
                unitPrice = v_unit;
            }
            v_element = doc.select("div.productText").first();
            if (v_element == null) {
                return null;
            } else {
                description = v_element.text();
            }
        } catch (IOException groceries_ex) {
            Logger.getLogger(Webscraperutil.class.getName()).log(Level.SEVERE, null, groceries_ex);
        }
        
        return new GroceriesInformation(title, size, unitPrice, description);
    }
    
    /**
     * Webscraper method is used to process given input url and return result as JSON.
    **/
    public String webScaper(URL url) {
        JSONObject json = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        json.put("results", jsonarr);
        float total = 0.0f;
       Connection con = Jsoup.connect(url.toString());
        //System.out.println(con);
        if (con == null) {
            return "{}";
        }
        try {
            Document doc = con.get();
            if (doc == null) {
                return "{}";
            }
            Elements link_element = doc.select("a[href*=groceries/ripe]");
            for (Element element: link_element) {
                //System.out.println(element.attr("abs:href"));
                String product_url = element.attr("abs:href");
                GroceriesInformation prdinfo = getProductInformations(product_url);
               // System.out.println(prdinfo);

                jsonarr.add(prdinfo.constructJsonObj());
                total += prdinfo.getUnitPrice();
            }
        } catch (IOException scraper_ex) {
            Logger.getLogger(Webscraperutil.class.getName()).log(Level.SEVERE, null, scraper_ex);
        }
        json.put("total", total);
        
        return json.toJSONString();
    }
}
