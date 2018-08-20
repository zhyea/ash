package org.chobit.ash.core.bean;

import org.chobit.ash.model.ParamMap;

import java.util.Map;

/**
 * @author robin
 */
public class Param {


    private ParamMap paramMap;

    public Param(Map<String, String> paramMap) {
        this.paramMap = new ParamMap(4);
        this.paramMap.putAll(paramMap);
    }


    public ParamMap getMap() {
        return paramMap;
    }
}
