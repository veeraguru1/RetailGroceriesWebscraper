package com.saingroceris.retailwebscraper;

/**
 * GroceriesInformation class is holding object module for groceries result JSON data.
 */
public class GroceriesInformation {
    private String title;
    private float size;
    private float unitPrice;
    private String description;

    public GroceriesInformation(String title, float size, float unitPrice, String description) {
        this.title = title;
        this.size = size;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }


    public void setSize(float size) {
        this.size = size;
    }
    public float getSize() {
        return size;
    }


    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public float getUnitPrice() {
        return unitPrice;
    }


    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return "{" + "title=" + title + ", size=" + size + ", unitPrice=" + unitPrice + ", description=" + description + '}';
    }
    
}
