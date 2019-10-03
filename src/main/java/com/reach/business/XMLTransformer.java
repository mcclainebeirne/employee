package com.reach.business;

import com.thoughtworks.xstream.XStream;

public class XMLTransformer {
    private XStream xstream = null;

    private XMLTransformer() {
        xstream = new XStream();
    }

    public static XMLTransformer getInstance() {
        return new XMLTransformer();
    }

    public String toXMLString(Object object) {
        return xstream.toXML(object);
    }

    public Object toObject(String xml) {
        return (Object) xstream.fromXML(xml);
    }

}