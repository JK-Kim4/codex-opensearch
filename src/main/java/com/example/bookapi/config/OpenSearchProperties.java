package com.example.bookapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opensearch")
public class OpenSearchProperties {

    private String host;
    private int port;
    private String scheme = "http";
    private String username;
    private String password;
    private final Index index = new Index();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Index getIndex() {
        return index;
    }

    public static class Index {

        private String book = "books";

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }
    }
}
