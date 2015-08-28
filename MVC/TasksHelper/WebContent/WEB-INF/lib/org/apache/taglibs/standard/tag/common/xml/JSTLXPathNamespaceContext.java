/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * glassfish/bootstrap/legal/CDDLv1.0.txt or
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * glassfish/bootstrap/legal/CDDLv1.0.txt.  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 *
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 *
 * Portions Copyright Apache Software Foundation.
 */
package org.apache.taglibs.standard.tag.common.xml;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.XMLConstants;

/**
  * Implemenation of XML Namespace context processing given a URI or prefix
  */
public class JSTLXPathNamespaceContext implements NamespaceContext {

    //*********************************************************************
    // Constructor

    /**
     * No-arg constructor which would create empty HashMap of namespaces
     */
    public JSTLXPathNamespaceContext() {
        namespaces = new HashMap();
    }

    public JSTLXPathNamespaceContext(HashMap nses) {
        namespaces = nses;
    }

    /**
     * The context to resolve the prefix from, if the context
     * is not given. 
     */
    HashMap namespaces;

    /**
     * Get Namespace URI bound to a prefix in the current scope
     *
     * @param Prefix Parameter
     *
     * @return Namespace URI bound to prefix
     *
     * @throws IllegalArgumentException if prefix is null
     */
    public String getNamespaceURI(String prefix) 
        throws IllegalArgumentException {
        // p("[getNamespaceURI] prefix: " + prefix);
        if (prefix == null) {
            throw new IllegalArgumentException("Cannot get Namespace URI for null prefix");
        }

        if (prefix.equals(XMLConstants.XML_NS_PREFIX)) {
            return XMLConstants.XML_NS_URI;
        }
        if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }

        String namespaceURI = (String)namespaces.get(prefix);
        // p("[getNamespaceURI] namespaceURI: " + namespaceURI);
        if (namespaceURI != null) {
            return namespaceURI;
        } 

        return XMLConstants.NULL_NS_URI;
    }

    /**
     * Get Prefix bound to Namespace URI in the current scope
     *
     * @param Namespace URI 
     *
     * @return Prefix bound to Namespace URI
     *
     * @throws IllegalArgumentException if Namespace URI is null
     */
    public String getPrefix(String namespaceURI) {
        // p("[getPrefix] namespaceURI: " + namespaceURI);
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Cannot get prefix for null NamespaceURI");
        }

        if (namespaceURI.equals(XMLConstants.XML_NS_URI)) {
            return XMLConstants.XML_NS_PREFIX;
        }
        if (namespaceURI.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
            return XMLConstants.XMLNS_ATTRIBUTE;
        }

        Iterator iter = namespaces.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String)iter.next();
            String value = (String)namespaces.get(key);
            if (value.equals(namespaceURI)) {
                // p("[getPrefix] value: " + value);
                return value;
            }
        }

        // p("[getPrefix] returning null");
        return null;
    }

    /**
     * Get all Prefixes bound to Namespace URI in the current scope
     *
     * @param Namespace URI 
     *
     * @return Iterator of Prefixes bound to Namespace URI
     *
     * @throws IllegalArgumentException if Namespace URI is null
     */
    public Iterator getPrefixes(String namespaceURI) {
        // p("[getPrefixes] namespaceURI: " + namespaceURI);
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Cannot get prefix for null NamespaceURI");
        }

        if (namespaceURI.equals(XMLConstants.XML_NS_URI)) {
            return Arrays.asList(new String[] {XMLConstants.XML_NS_PREFIX}).iterator();
        }
        if (namespaceURI.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
            return Arrays.asList(new String[] {XMLConstants.XMLNS_ATTRIBUTE}).iterator();
        }

        ArrayList prefixList = new ArrayList();
        Iterator iter = namespaces.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String)iter.next();
            String value = (String)namespaces.get(key);
            if (value.equals(namespaceURI)) {
                prefixList.add(key);
            }
        }

        // p("[getPrefixes] prefixList: " + prefixList);
        return prefixList.iterator();
    }

    /**
     * Populate map of Prefix and NameSpace URI's entries
     */
    protected void addNamespace(String prefix, String uri ) {
        namespaces.put(prefix, uri );
    }

    //*********************************************************************
    // Utility methods

    private static void p(String s) {
        System.out.println("[JSTLXPathNameContext] " + s);
    }
}
