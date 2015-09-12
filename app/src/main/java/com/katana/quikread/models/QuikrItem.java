package com.katana.quikread.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public class QuikrItem<T> {

    private transient int primaryKey;

    @SerializedName("attribute_Ad_Type")
    private String attributeAdType;

    private String cityName;

    private String metaCategoryName;

    @SerializedName("ad_locality")
    private String adLocality;

    private String content;

    private T images;

    private String id;

    private String title;

    @SerializedName("attribute_Genre")
    private String attributeGenre;

    private String url;

    private String geoPin; //TODO : maybe show on map

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getAttributeAdType() {
        return attributeAdType;
    }

    public void setAttributeAdType(String attributeAdType) {
        this.attributeAdType = attributeAdType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMetaCategoryName() {
        return metaCategoryName;
    }

    public void setMetaCategoryName(String metaCategoryName) {
        this.metaCategoryName = metaCategoryName;
    }

    public String getAdLocality() {
        return adLocality;
    }

    public void setAdLocality(String adLocality) {
        this.adLocality = adLocality;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getImages() {
        return images;
    }

    public void setImages(T images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttributeGenre() {
        return attributeGenre;
    }

    public void setAttributeGenre(String attributeGenre) {
        this.attributeGenre = attributeGenre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGeoPin() {
        return geoPin;
    }

    public void setGeoPin(String geoPin) {
        this.geoPin = geoPin;
    }
}
