package org.chobit.ash.core.bean;

import org.chobit.ash.core.enums.RequestMethod;

import java.util.Objects;

/**
 * @author robin
 */
public class Request {

    private String path;

    private RequestMethod method;

    private String[] headers;


    public Request(String path, RequestMethod method) {
        this.path = path;
        this.method = method;
    }

    public Request(String path, RequestMethod method, String[] headers) {
        this.path = path;
        this.method = method;
        this.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(path, request.path) &&
                method == request.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }
}
