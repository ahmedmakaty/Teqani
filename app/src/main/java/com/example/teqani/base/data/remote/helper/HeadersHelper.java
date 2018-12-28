package com.example.teqani.base.data.remote.helper;

import java.util.HashMap;
import java.util.Map;

public class HeadersHelper {

    public static Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");

        return headers;
    }
}
