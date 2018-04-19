package com.saingroceris.retailwebscraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WebscraperutilTest {
    Webscraperutil webscaper;


    public WebscraperutilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        URL url;
        try {
            url = new URL("https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true");
            webscaper = new Webscraperutil();
            webscaper.webScaper(url);
        } catch (MalformedURLException test_ex) {
            Logger.getLogger(WebscraperutilTest.class.getName()).log(Level.SEVERE, null, test_ex);
        }
    }

    @After
    public void tearDown() {
        webscaper = null;
    }

    @Test
    public void testCheckValidRetailUrl() {

        URL url;
        try {
            url = new URL("https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true");
            String json = webscaper.webScaper(url);
            assertTrue(!json.isEmpty());
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebscraperutilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testCheckTotalUnitPrice() {

        URL url;
        try {
            url = new URL("https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true");
            String json = webscaper.webScaper(url);
            assertTrue(json.contains("total") && json.contains("results"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebscraperutilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testCheckValidUrl() {
        String url = "http://www.sainsburys.co.uk";
        GroceriesInformation product = webscaper.getProductInformations(url);
        assertNull(product);
    }

    @Test
    public void testCheckFailureProductInformation() {
        String url = "https://www.sainsburys.co.uk/shop/gb/groceries/ripe---ready/sainsburys-avocado--ripe---ready-x2";
        GroceriesInformation product = webscaper.getProductInformations(url);

        assertNotNull(product);
        assertNotNull(product.getTitle());
        assertTrue(product.getSize() > 0);
        assertTrue(product.getUnitPrice() == 1.5f);
        assertNotNull(product.getDescription());
        assertTrue(product.getDescription().length() > 0);
    }


    @Test
    public void testCheckValidProductInformation() {
        String url = "https://www.sainsburys.co.uk/shop/gb/groceries/ripe---ready/sainsburys-avocado--ripe---ready-x2";
        GroceriesInformation product = webscaper.getProductInformations(url);

        assertNotNull(product);
        assertNotNull(product.getTitle());
        assertTrue(product.getSize() > 0);
        assertTrue(product.getUnitPrice() == 1.9f);
        assertNotNull(product.getDescription());
        assertTrue(product.getDescription().length() > 0);
    }

    @Test
    public void testCheckProductDescriptionInfo() {

        URL url;
        try {
            url = new URL("https://www.sainsburys.co.uk/shop/gb/groceries/ripe---ready/sainsburys-avocado--ripe---ready-x2");
            String json = webscaper.webScaper(url);
            assertTrue(json.contains("Avocado"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebscraperutilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


