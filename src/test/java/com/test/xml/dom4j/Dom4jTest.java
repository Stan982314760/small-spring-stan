package com.test.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: stan
 * @Date: 2021/10/06
 * @Description:
 */
public class Dom4jTest {

    @Test
    public void test() throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Student.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        //for (Element element : rootElement.elements()) {
        //    if ("hobby".equals(element.getName())) {
        //        for (Element e : element.elements()) {
        //            System.out.println(e.getText() + " xxx");
        //        }
        //    }
        //}
        //
        List<Node> nodes = document.selectNodes("//hobby");
        System.out.println(nodes.get(0).getName());
        System.out.println(((Element) nodes.get(0)).content().size());

    }

    @Test
    public void testRegex() {
        String sql = "SELECT * FROM stu WHERE id = #   {id}";
        Pattern pattern = Pattern.compile("#(\\s*)(\\{.*?})");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            //System.out.println(matcher.group(3));
        }
    }
}
