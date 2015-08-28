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

package org.apache.taglibs.standard.tag.common.core;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>Support for tag handlers for &lt;redirect&gt;, JSTL 1.0's tag
 * for redirecting to a new URL (with optional query parameters).</p>
 *
 * @author Shawn Bayern
 */

public abstract class RedirectSupport extends BodyTagSupport
    implements ParamParent {

    //*********************************************************************
    // Protected state

    protected String url;                        // 'url' attribute
    protected String context;                    // 'context' attribute

    //*********************************************************************
    // Private state

    private String var;                          // 'var' attribute
    private int scope;				 // processed 'scope' attr
    private ParamSupport.ParamManager params;	 // added parameters

    //*********************************************************************
    // Constructor and initialization

    public RedirectSupport() {
	super();
	init();
    }

    private void init() {
	url = var = null;
	params = null;
	scope = PageContext.PAGE_SCOPE;
    }


   //*********************************************************************
    // Tag attributes known at translation time

    public void setVar(String var) {
        this.var = var;
    }

    public void setScope(String scope) {
	this.scope = Util.getScope(scope);
    }


    //*********************************************************************
    // Collaboration with subtags

    // inherit Javadoc
    public void addParameter(String name, String value) {
	params.addParameter(name, value);
    }


    //*********************************************************************
    // Tag logic

    // resets any parameters that might be sent
    public int doStartTag() throws JspException {
	params = new ParamSupport.ParamManager();
	return EVAL_BODY_BUFFERED;
    }


    // gets the right value, encodes it, and prints or stores it
    public int doEndTag() throws JspException {
	String result;				// the eventual result

	// add (already encoded) parameters
        String baseUrl = UrlSupport.resolveUrl(url, context, pageContext);
        result = params.aggregateParams(baseUrl);

        // if the URL is relative, rewrite it with 'redirect' encoding rules
        HttpServletResponse response =
            ((HttpServletResponse) pageContext.getResponse());
        if (!ImportSupport.isAbsoluteUrl(result))
            result = response.encodeRedirectURL(result);

	// redirect!
	try {
	    response.sendRedirect(result);
	} catch (java.io.IOException ex) {
	    throw new JspTagException(ex.toString(), ex);
	}

	return SKIP_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
