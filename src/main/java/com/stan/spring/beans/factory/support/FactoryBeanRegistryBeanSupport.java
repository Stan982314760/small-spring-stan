package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 关于单例有两个缓存: 第一个缓存是 singletonObjects, 第二个缓存是 factoryBeanObjectCache
 * singletonObjects: 缓存一般对象和实现了FactoryBean接口的对象
 * factoryBeanObjectCache: 缓存FactoryBean#getObject()返回的对象
 *
 * 1. 先去访问singletonObjects，如果不存在，则走创建对象逻辑
 * 2. InstantiateStrategy创建完对象后，执行依赖注入和初始化方法，如果是单例则加入singletonObjects缓存
 *      注意！！！这里加入的可能是FactoryBean对象
 * 3. 拿到通过1或者2步骤返回的对象，再从这个对象中获取真正对象
 *    3.1 如果这个对象不是FactoryBean对象 则直接返回即可
 *    3.2 如果这个对象是FactoryBean对象 则先通过beanName去访问factoryBeanObjectCache缓存 如果取到的值不为null 直接返回
 *    3.3 如果factoryBeanObjectCache缓存中不存在 则调用FactoryBean#getObject()创建对象 然后将创建对象加入factoryBeanObjectCache缓存
 *
 * 4.   为了区分factoryBeanObjectCache中是真的不存在beanName的映射，还是因为getObject()返回了null 导致拿到的是null
 *      所以将 FactoryBean#getObject() => null 情况，在缓存中保存为一个 NUL_OBJ 进去区分
 *      还有就是ConcurrentHashMap的Key和Value都不能为null 所以要避免将getObject()返回的null访问u缓存
 *
 * 5. 对于FactoryBean singletonObjects和factoryBeanObjectCache中的beanName都是一样的！！！
 *
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: FactoryBeanRegistryBeanSupport
 */
public class FactoryBeanRegistryBeanSupport extends DefaultSingletonBeanRegistry {


    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();


    public Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
       if (factoryBean.isSingleton()) {
           Object obj = factoryBeanObjectCache.get(beanName);
           if (obj == null) {
               obj = doGetObjectFromFactoryBean(factoryBean);
               factoryBeanObjectCache.put(beanName, (obj == null ? NUL_OBJ : obj));
           }
           return (obj == NUL_OBJ ? null : obj);
       } else {
           return doGetObjectFromFactoryBean(factoryBean);
       }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean) {
        Object obj;
        try {
            obj = factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean getObject error", e);
        }
        return obj;
    }

}
