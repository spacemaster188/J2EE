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

package org.apache.taglibs.standard.tag.el.core;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.common.core.OutSupport;

/**
 * <p>A handler for &lt;out&gt;, which redirects the browser to a
 * new URL.
 *
 * @author Shawn Bayern
 */

public class OutTag extends OutSupport {

    //*********************************************************************
    // 'Private' state (implementation details)

    private String value_;			// stores EL-based property
    private String default_;			// stores EL-based property
    private String escapeXml_;			// stores EL-based property


    //*********************************************************************
    // Constructor

    public OutTag() {
        super();
        init();
    }


    //*********************************************************************
    // Tag logic

    // evaluates expression and chains to parent
    public int doStartTag() throws JspException {

        // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();

	// chain to the parent implementation
	return super.doStartTag();
    }


    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }


    //*********************************************************************
    // Accessor methods

    public void setValue(String value_) {
        this.value_ = value_;
    }

    public void setDefault(String default_) {
        this.default_ = default_;
    }

    public void setEscapeXml(String escapeXml_) {
        this.escapeXml_ = escapeXml_;
    }


    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)
    private void init() {
        // null implies "no expression"
	value_ = default_ = escapeXml_ = null;
    }

    /* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {
	try {
	    value = ExpressionUtil.evalNotNull(
	        "out", "value", value_, Object.class, this, pageContext);
	} catch (NullAttributeException ex) {
	    // explicitly allow 'null' for value
	    value = null;
	}
	try { 
	    def = (String) ExpressionUtil.evalNotNull(
	        "out", "default", default_, String.class, this, pageContext);
	} catch (NullAttributeException ex) {
	    // explicitly allow 'null' for def
	    def = null;
	}
	escapeXml = true;
	Boolean escape = ((Boolean) ExpressionUtil.evalNotNull(
	    "out", "escapeXml", escapeXml_, Boolean.class, this, pageContext));
	if (escape != null)
	    escapeXml = escape.booleanValue();
    }
}
