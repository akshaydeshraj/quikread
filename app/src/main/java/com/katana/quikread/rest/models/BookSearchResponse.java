package com.katana.quikread.rest.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */

@Root(name = "GoodreadsResponse", strict = false)
public class BookSearchResponse {

    @Element(name = "Request")
    Request request;

    public Request getRequest() {
        return request;
    }

    @Root
    public static class Request {
        @Element(name = "key")
        String key;

        @Element(name = "method")
        String method;

        @Element(name = "authentication")
        String authentication;

        public String getKey() {
            return key;
        }

        public String getMethod() {
            return method;
        }

        public String getAuthentication() {
            return authentication;
        }
    }

    @Root
    public static class Book {

        @Element(name = "id")
        String id;

        @Element(name = "title")
        String title;

        @Element(name = "isbn")
        String isbn;

        @Element(name = "isbn13")
        String isbn13;

        @Element(name = "asin")
        String asin;

        @Element(name = "image_url")
        String imageUrl;

        @Element(name = "small_image_url")
        String smallImageUrl;

        @Element(name = "description")
        String description;
    }
}

