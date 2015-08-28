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

package org.apache.taglibs.standard.tag.rt.xml;

import javax.servlet.jsp.JspTagException;
import javax.xml.transform.Result;

import org.apache.taglibs.standard.tag.common.xml.TransformSupport;

/**
 * <p>A handler for &lt;transform&gt; that supports rtexprvalue-based
 * attributes.</p>
 *
 * @author Shawn Bayern
 */

public class TransformTag extends TransformSupport {

    //*********************************************************************
    // Accessor methods

    // Deprecated as of JSTL 1.1
    // for tag attribute
    public void setXml(Object xml) throws JspTagException {
        this.xml = xml;
    }

    // 'doc' replaces 'xml' as of JSTL 1.1
    public void setDoc(Object xml) throws JspTagException {
        this.xml = xml;
    }

    // Deprecated as of JSTL 1.1
    // for tag attribute
    public void setXmlSystemId(String xmlSystemId) throws JspTagException {
        this.xmlSystemId = xmlSystemId;
    }

    // 'docSystemId' replaces 'xmlSystemId' as of JSTL 1.1
    public void setDocSystemId(String xmlSystemId) throws JspTagException {
        this.xmlSystemId = xmlSystemId;
    }

    // for tag attribute
    public void setXslt(Object xslt) throws JspTagException {
        this.xslt = xslt;
    }

    // for tag attribute
    public void setXsltSystemId(String xsltSystemId) throws JspTagException {
        this.xsltSystemId = xsltSystemId;
    }

    // for tag attribute
    public void setResult(Result result) throws JspTagException {
        this.result = result;
    }

}
