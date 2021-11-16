package com.stan.spring.beans.factory.support.xml;

import cn.hutool.core.util.StrUtil;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.PropertyValue;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanReference;
import com.stan.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.stan.spring.beans.factory.support.BeanDefinitionRegistry;
import com.stan.spring.core.io.Resource;
import com.stan.spring.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: XmlBeanDefinitionReader
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry,
                                   ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(String location) {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinition(resource);
    }


    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            loadBeanDefinition(location);
        }
    }

    @Override
    public void loadBeanDefinition(Resource resource) {
        doLoadBeanDefinition(resource);
    }

    private void doLoadBeanDefinition(Resource resource) {
        Map<String, BeanDefinition> beanDefinitionMap = parseXml(resource);
        if (beanDefinitionMap != null && beanDefinitionMap.size() > 0) {
            beanDefinitionMap.forEach(getRegistry()::registerBeanDefinition);
        }
    }


    private Map<String, BeanDefinition> parseXml(Resource resource) {
        Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(resource.getInputStream());
            Element root = document.getRootElement();
            List<Element> beanElements = root.elements("bean");
            for (Element beanElement : beanElements) {
                // id name class
                String id = beanElement.attributeValue("id");
                String name = beanElement.attributeValue("name");
                String className = beanElement.attributeValue("class");
                Class<?> clazz = Class.forName(className);
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    beanName = StrUtil.lowerFirst(className);
                }

                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                if (beanDefinition != null) {
                    throw new BeansException("duplicate bean found : " + beanName);
                }
                beanDefinitionMap.put(beanName, new BeanDefinition(clazz));
                beanDefinition = beanDefinitionMap.get(beanName);

                // init-method destroy-method
                String initMethodName = beanElement.attributeValue("init-method");
                if (StrUtil.isNotEmpty(initMethodName)) {
                    beanDefinition.setInitMethodName(initMethodName);
                }
                String destroyMethodName = beanElement.attributeValue("destroy-method");
                if (StrUtil.isNotEmpty(destroyMethodName)) {
                    beanDefinition.setDestroyMethodName(destroyMethodName);
                }

                // scope
                String scope = beanElement.attributeValue("scope");
                if (StrUtil.isNotEmpty(scope)) {
                    beanDefinition.setScope(scope);
                }

                // property
                List<Element> propertyElements = beanElement.elements("property");
                for (Element propertyElement : propertyElements) {
                    String pvName = propertyElement.attributeValue("name");
                    String pvValue = propertyElement.attributeValue("value");
                    String refName = propertyElement.attributeValue("ref");

                    PropertyValue pv;
                    if (StrUtil.isNotEmpty(refName)) {
                        pv = new PropertyValue(pvName, new BeanReference(refName));
                    } else {
                        pv = new PropertyValue(pvName, pvValue);
                    }
                    beanDefinition.getPropertyValues().addPropertyValue(pv);
                }
            }
        } catch (DocumentException | IOException | ClassNotFoundException e) {
            throw new BeansException(e);
        }

        return beanDefinitionMap;
    }
}
