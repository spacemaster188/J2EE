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

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.tag.common.fmt.FormatNumberSupport;

/**
 * <p>A handler for &lt;formatNumber&gt; that supports rtexprvalue-based
 * attributes.</p>
 *
 * @author Jan Luehe
 */

public class FormatNumberTag extends FormatNumberSupport {

    //*********************************************************************
    // Accessor methods

    // 'value' attribute
    public void setValue(Object value) throws JspTagException {
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

    // 'currencyCode' attribute
    public void setCurrencyCode(String currencyCode) throws JspTagException {
        this.currencyCode = currencyCode;
    }

    // 'currencySymbol' attribute
    public void setCurrencySymbol(String currencySymbol)
	throws JspTagException {
        this.currencySymbol = currencySymbol;
    }

    // 'groupingUsed' attribute
    public void setGroupingUsed(boolean isGroupingUsed)
	throws JspTagException {
        this.isGroupingUsed = isGroupingUsed;
	this.groupingUsedSpecified = true;
    }

    // 'maxIntegerDigits' attribute
    public void setMaxIntegerDigits(int maxDigits) throws JspTagException {
        this.maxIntegerDigits = maxDigits;
	this.maxIntegerDigitsSpecified = true;
    }

    // 'minIntegerDigits' attribute
    public void setMinIntegerDigits(int minDigits) throws JspTagException {
        this.minIntegerDigits = minDigits;
	this.minIntegerDigitsSpecified = true;
    }

    // 'maxFractionDigits' attribute
    public void setMaxFractionDigits(int maxDigits) throws JspTagException {
        this.maxFractionDigits = maxDigits;
	this.maxFractionDigitsSpecified = true;
    }

    // 'minFractionDigits' attribute
    public void setMinFractionDigits(int minDigits) throws JspTagException {
        this.minFractionDigits = minDigits;
	this.minFractionDigitsSpecified = true;
    }
}
