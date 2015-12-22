package com.mteng.util;

/**
 * Created by mteng on 12/21/2015.
 */
public final class ServiceConstants {

    public static final String QUESTIONMARK = "?";

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String SORT_BY = "sortBy";
    public static final String SORT_ORDER = "sortOrder";
    public static final String HOST = "localhost";
    public static final String PORT = "11301";

    public static final String ID = "id"; // is constant because it's used for the controller mapping

    private ServiceConstants() {
        throw new AssertionError();
    }
}