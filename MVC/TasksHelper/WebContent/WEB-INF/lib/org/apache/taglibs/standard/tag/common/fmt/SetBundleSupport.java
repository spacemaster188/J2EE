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

package org.apache.taglibs.standard.tag.common.fmt;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.Util;

/**
 * Support for tag handlers for &lt;setBundle&gt;, the JSTL 1.0 tag that loads
 * a resource bundle and stores it in a scoped variable.
 *
 * @author Jan Luehe
 */

public abstract class SetBundleSupport extends TagSupport {

    
    //*********************************************************************
    // Protected state

    protected String basename;                  // 'basename' attribute


    //*********************************************************************
    // Private state

    private int scope;                          // 'scope' attribute
    private String var;                         // 'var' attribute


    //*********************************************************************
    // Constructor and initialization

    public SetBundleSupport() {
	super();
	init();
    }

    private void init() {
	basename = null;
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
    // Tag logic

    public int doEndTag() throws JspException {
	LocalizationContext locCtxt =
	    BundleSupport.getLocalizationContext(pageContext, basename);

	if (var != null) {
	    pageContext.setAttribute(var, locCtxt, scope);
	} else {
	    Config.set(pageContext, Config.FMT_LOCALIZATION_CONTEXT, locCtxt,
		       scope);
	}

	return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
