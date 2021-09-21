package com.stan.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: PropertyValues
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String name) {
        PropertyValue pv = null;
        for (PropertyValue propertyValue : propertyValueList) {
            if (name.equals(propertyValue.getName())) {
                pv = propertyValue;
            }
        }
        return pv;
    }
}
