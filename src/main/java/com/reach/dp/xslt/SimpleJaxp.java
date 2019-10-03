package com.reach.dp.xslt;

import com.google.common.base.CaseFormat;

import java.io.*;


/**
 * A simple demo of JAXP 1.1
 */
public class SimpleJaxp {

    /**
     * Accept two command line arguments: the name of an XML file, and
     * the name of an XSLT stylesheet. The result of the transformation
     * is written to stdout.
     */
    public static void main(String[] args)
            throws javax.xml.transform.TransformerException {
        System.out.println(new Department('1',"mac").toString());
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "dept_manager"));

        if (args.length != 2) {
            System.err.println("Usage:");
            System.err.println("  java " + SimpleJaxp.class.getName( )
                    + " xmlFileName xsltFileName");
            System.exit(1);
        }

        File xmlFile = new File(args[0]);
        File xsltFile = new File(args[1]);

        javax.xml.transform.Source xmlSource =
                new javax.xml.transform.stream.StreamSource(xmlFile);
        javax.xml.transform.Source xsltSource =
                new javax.xml.transform.stream.StreamSource(xsltFile);
        javax.xml.transform.Result result =
                new javax.xml.transform.stream.StreamResult(System.out);

        // create an instance of TransformerFactory
        javax.xml.transform.TransformerFactory transFact =
                javax.xml.transform.TransformerFactory.newInstance( );

        javax.xml.transform.Transformer trans =
                transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, result);
    }
}
