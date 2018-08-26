package org.chobit.ash.core.enums;

/**
 * @author robin
 */
public enum MediaType {

    /**
     * application/json
     */
    APPLICATION_JSON("application/json"),
    ;

    public final String value;

    MediaType(String value) {
        this.value = value;
    }
}
