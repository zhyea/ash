package org.chobit.ash.core.bean;

import org.chobit.ash.model.ParamMap;

/**
 * @author robin
 */
public class View {


    private String path;

    private ParamMap model;

    public View(String path) {
        this.path = path;
        this.model = new ParamMap(2);
    }


    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }


    public String getPath() {
        return path;
    }


    public ParamMap getModel() {
        return model;
    }
}
