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

package org.apache.taglibs.standard.extra.spath;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Tag handler that exposes SPath functionality.</p>
 *
 * @author Shawn Bayern
 */

public class SPathTag extends TagSupport {

    //*********************************************************************
    // Internal state

    private String select;                       // tag attribute
    private String var;				 // tag attribute

    //*********************************************************************
    // Construction and initialization

    /**
     * Constructs a new handler.  As with TagSupport, subclasses should
     * not provide other constructors and are expected to call the
     * superclass constructor.
     */
    public SPathTag() {
        super();
        init();
    }

    // resets local state
    private void init() {
	select = var = null;
    }


    //*********************************************************************
    // Tag logic

    // applies XPath expression from 'select' and exposes a filter as 'var'
    public int doStartTag() throws JspException {
      try {
	SPathFilter s = new SPathFilter(new SPathParser(select).expression());
	pageContext.setAttribute(var, s);
	return SKIP_BODY;
      } catch (ParseException ex) {
	throw new JspTagException(ex.toString(), ex);
      }
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
}
