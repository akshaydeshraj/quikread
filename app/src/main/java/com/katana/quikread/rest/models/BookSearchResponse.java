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

    @Element(name = "book")
    Book book;

    public Request getRequest() {
        return request;
    }

    public Book getBook() {
        return book;
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

    @Root(strict = false)
    public static class Book {

        @Element(name = "id", required = false)
        String id;

        @Element(name = "title", required = false)
        String title;

        @Element(name = "isbn", required = false)
        String isbn;

        @Element(name = "image_url", required = false)
        String imageUrl;

        @Element(name = "small_image_url", required = false)
        String smallImageUrl;

        @Element(name = "description", required = false)
        String description;

        @Element(name = "average_rating", required = false)
        String averageRating;

        @Element(name = "authors", required = false)
        Authors authors;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSmallImageUrl() {
            return smallImageUrl;
        }

        public String getDescription() {
            return description;
        }

        public String getAverageRating() {
            return averageRating;
        }

        public Authors getAuthors() {
            return authors;
        }
    }

    @Root(strict = false)
    public static class Authors {

        @Element(name = "author", required = false)
        Author author;

        public Author getAuthor() {
            return author;
        }
    }

    @Root(strict = false)
    public static class Author {

        @Element(name = "name", required = false)
        String name;

        public String getName() {
            return name;
        }
    }
}

