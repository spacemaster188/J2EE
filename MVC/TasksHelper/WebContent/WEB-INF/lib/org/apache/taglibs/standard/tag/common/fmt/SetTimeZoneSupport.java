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

import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.Util;

/**
 * Support for tag handlers for &lt;setTimeZone&gt;, the time zone setting tag
 * in JSTL 1.0.
 *
 * @author Jan Luehe
 */

public abstract class SetTimeZoneSupport extends TagSupport {

    
    //*********************************************************************
    // Protected state

    protected Object value;                      // 'value' attribute


    //*********************************************************************
    // Private state

    private int scope;                           // 'scope' attribute
    private String var;                          // 'var' attribute


    //*********************************************************************
    // Constructor and initialization

    public SetTimeZoneSupport() {
	super();
	init();
    }

    // resets local state
    private void init() {
	value = var = null;
	scope = PageContext.PAGE_SCOPE;
    }


   //*********************************************************************
    // Tag attributes known at translation time

    public void setScope(String scope) {
	this.scope = Util.getScope(scope);
    }

    public void setVar(String var) {
        this.var = var;
    }


    //*********************************************************************
    // Tag logic

    public int doEndTag() throws JspException {
	TimeZone timeZone = null;

	if (value == null) {
	    timeZone = TimeZone.getTimeZone("GMT");
	} else if (value instanceof String) {
	    if (((String) value).trim().equals("")) {
		timeZone = TimeZone.getTimeZone("GMT");
	    } else {
		timeZone = TimeZone.getTimeZone((String) value);
	    }
	} else {
	    timeZone = (TimeZone) value;
	}

	if (var != null) {
	    pageContext.setAttribute(var, timeZone, scope);
	} else {
	    Config.set(pageContext, Config.FMT_TIME_ZONE, timeZone, scope);
	}

	return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
