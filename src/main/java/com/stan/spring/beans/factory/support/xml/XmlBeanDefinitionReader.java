package com.stan.spring.beans.factory.support.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.PropertyValue;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanReference;
import com.stan.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.stan.spring.beans.factory.support.BeanDefinitionRegistry;
import com.stan.spring.core.io.Resource;
import com.stan.spring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.HashMap;
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
            Document doc = XmlUtil.readXML(resource.getInputStream());
            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    if ("bean".equals(item.getNodeName())) {
                        Element bean = (Element) item;
                        String id = bean.getAttribute("id");
                        String name = bean.getAttribute("name");
                        String className = bean.getAttribute("class");
                        Class<?> beanClass = Class.forName(className);
                        String beanName = StrUtil.isNotEmpty(id) ? id : name;
                        if (StrUtil.isEmpty(beanName)) {
                            beanName = StrUtil.lowerFirst(beanClass.getSimpleName());
                        }
                        if (beanDefinitionMap.containsKey(beanName))
                            throw new BeansException("duplicate bean found : " + beanName);

                        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                        if (beanDefinition == null) {
                            beanDefinitionMap.putIfAbsent(beanName, new BeanDefinition(beanClass));
                        }
                        beanDefinition = beanDefinitionMap.get(beanName);
                        beanDefinition.setInitMethodName(bean.getAttribute("init-method"));
                        beanDefinition.setDestroyMethodName(bean.getAttribute("destroy-method"));

                        String scope = bean.getAttribute("scope");
                        if (StrUtil.isNotEmpty(scope)) {
                            beanDefinition.setScope(scope);
                        }


                        NodeList beanChildNodes = bean.getChildNodes();
                        for (int j = 0; j < beanChildNodes.getLength(); j++) {
                            if (beanChildNodes.item(j) instanceof Element) {
                                if ("property".equals(beanChildNodes.item(j).getNodeName())) {
                                    Element property = (Element) beanChildNodes.item(j);
                                    String pvName = property.getAttribute("name");
                                    String pvValue = property.getAttribute("value");
                                    String ref = property.getAttribute("ref");

                                    PropertyValue pv;
                                    if (StrUtil.isNotEmpty(ref)) {
                                        pv = new PropertyValue(pvName, new BeanReference(ref));
                                    } else {
                                        pv = new PropertyValue(pvName, pvValue);
                                    }
                                    beanDefinition.getPropertyValues().addPropertyValue(pv);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException(e);
        }

        return beanDefinitionMap;
    }
}
