package com.stan.spring.context.support;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: ClassPathXmlApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractXmlRefreshableApplicationContext {

    private final String[] locations;


    public ClassPathXmlApplicationContext(String location) {
        this(new String[]{location});
    }

    public ClassPathXmlApplicationContext(String[] locations) {
        this.locations = locations;
        refresh();
    }


    @Override
    protected String[] getConfigLocations() {
        return locations;
    }


}
