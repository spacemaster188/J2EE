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

package org.apache.taglibs.standard.tag.el.xml;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.common.xml.ParseSupport;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.xml.sax.XMLFilter;

/**
 * <p>A handler for &lt;parse&gt; that accepts attributes as Strings
 * and evaluates them as expressions at runtime.</p>
 *
 * @author Shawn Bayern
 */
public class ParseTag extends ParseSupport {

    //*********************************************************************
    // 'Private' state (implementation details)

    private String xml_;                    // stores EL-based property
    private String systemId_;		    // stores EL-based property
    private String filter_;		    // stores EL-based property


    //*********************************************************************
    // Constructor

    /**
     * Constructs a new ParseTag.  As with TagSupport, subclasses
     * should not provide other constructors and are expected to call
     * the superclass constructor
     */
    public ParseTag() {
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

    // for EL-based attribute
    public void setFilter(String filter_) {
        this.filter_ = filter_;
    }

    public void setXml(String xml_) {
        this.xml_ = xml_;
    }

    public void setSystemId(String systemId_) {
        this.systemId_ = systemId_;
    }


    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)
    private void init() {
        // null implies "no expression"
	filter_ = xml_ = systemId_ = null;
    }

    /* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {
        /* 
         * Note: we don't check for type mismatches here; we assume
         * the expression evaluator will return the expected type
         * (by virtue of knowledge we give it about what that type is).
         * A ClassCastException here is truly unexpected, so we let it
         * propagate up.
         */

	xml = ExpressionUtil.evalNotNull(
	    "parse", "xml", xml_, Object.class, this, pageContext);
	systemId = (String) ExpressionUtil.evalNotNull(
	    "parse", "systemId", systemId_, String.class, this, pageContext);

	try {
	    filter = (XMLFilter) ExpressionUtil.evalNotNull(
	        "parse", "filter", filter_, XMLFilter.class, this, pageContext);
	} catch (NullAttributeException ex) {
	    // explicitly let 'filter' be null
	    filter = null;
	}
    }
}
