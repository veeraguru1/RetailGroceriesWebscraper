package com.saingroceris.retailwebscraper;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Class will act as controller for scrapping retail groceries web page
 */
public class Retailwebscrapermain {
    public static final void main(String[] args) {
        String inputurl = "https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";

        if (args.length == 1) {
            inputurl = args[0];
        }
        try {
            URL url = new URL(inputurl);
            Webscraperutil scraper = new Webscraperutil();
            System.out.println(scraper.webScaper((url)));

        } catch (MalformedURLException ex) {
            System.out.println("Invalid url :" + inputurl);
        }
    }
}
