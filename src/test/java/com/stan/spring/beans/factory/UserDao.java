package com.stan.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: UserDao
 */
public class UserDao {

    private static final Map<String, String> MAP = new HashMap<>(4);

    static {
        MAP.put("1", "stan");
        MAP.put("2", "monica");
        MAP.put("3", "andy");
    }


    public String queryUser(String userId) {
        return MAP.get(userId);
    }
}
