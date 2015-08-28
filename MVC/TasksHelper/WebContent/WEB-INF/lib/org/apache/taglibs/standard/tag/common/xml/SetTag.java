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

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.Util;

/**
 * <p>Tag handler for &lt;set&gt; in JSTL's XML library.</p>
 *
 * @author Shawn Bayern
 */
public class SetTag extends TagSupport {

    //*********************************************************************
    // Internal state

    private String select;                    // tag attribute
    private String var;                       // tag attribute
    private int scope;			      // processed tag attribute

    //*********************************************************************
    // Construction and initialization

    /**
     * Constructs a new handler.  As with TagSupport, subclasses should
     * not provide other constructors and are expected to call the
     * superclass constructor.
     */
    public SetTag() {
        super();
        init();
    }

    // resets local state
    private void init() {
	var = null;
	select = null;
        scope = PageContext.PAGE_SCOPE;
    }


    //*********************************************************************
    // Tag logic

    // applies XPath expression from 'select' and stores the result in 'var'
    public int doStartTag() throws JspException {
        // process the query
        XPathUtil xu = new XPathUtil(pageContext);
        List result =
        xu.selectNodes(XPathUtil.getContext(this), select);
        Object ret = result;
        
        // unwrap primitive types if that's what we received
        if (result.size() == 1) {
            Object o = result.get(0);
            if (o instanceof String || o instanceof Boolean
            || o instanceof Number)
                ret = o;
        }
        
        // expose the final result
        pageContext.setAttribute(var, ret, scope);
        return SKIP_BODY;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }


    //*********************************************************************
    // Attribute accessors

    public void setSelect(String select) {
	this.select = select;
    }

    public void setVar(String var) {
	this.var = var;
    }

    public void setScope(String scope) {
	this.scope = Util.getScope(scope);
    }
}
