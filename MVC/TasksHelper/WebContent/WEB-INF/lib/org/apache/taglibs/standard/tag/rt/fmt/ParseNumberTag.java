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

package org.apache.taglibs.standard.tag.rt.fmt;

import java.util.Locale;

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.tag.common.fmt.ParseNumberSupport;
import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;

/**
 * <p>A handler for &lt;parseNumber&gt; that supports rtexprvalue-based
 * attributes.</p>
 *
 * @author Jan Luehe
 */

public class ParseNumberTag extends ParseNumberSupport {

    //*********************************************************************
    // Accessor methods

    // 'value' attribute
    public void setValue(String value) throws JspTagException {
        this.value = value;
	this.valueSpecified = true;
    }

    // 'type' attribute
    public void setType(String type) throws JspTagException {
        this.type = type;
    }

    // 'pattern' attribute
    public void setPattern(String pattern) throws JspTagException {
        this.pattern = pattern;
    }

    // 'parseLocale' attribute
    public void setParseLocale(Object loc) throws JspTagException {
	if (loc != null) {
	    if (loc instanceof Locale) {
		this.parseLocale = (Locale) loc;
	    } else {
		if (!"".equals((String) loc)) {
		    this.parseLocale = SetLocaleSupport.parseLocale((String)
								    loc);
		}
	    }
	}
    }

    // 'integerOnly' attribute
    public void setIntegerOnly(boolean isIntegerOnly) throws JspTagException {
        this.isIntegerOnly = isIntegerOnly;
	this.integerOnlySpecified = true;
    }
}
