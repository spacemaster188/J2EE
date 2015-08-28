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
import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.common.core.WhenTagSupport;

/**
 * <p>Tag handler for &lt;when&gt; in JSTL's expression-evaluating
 * library.</p>
 *
 * @author Shawn Bayern
 */

public class WhenTag extends WhenTagSupport {

    //*********************************************************************
    // Constructor and lifecycle management

    // initialize inherited and local state
    public WhenTag() {
        super();
        init();
    }

    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }


    //*********************************************************************
    // Supplied conditional logic

    protected boolean condition() throws JspTagException {
        try { 
            Object r = ExpressionEvaluatorManager.evaluate(
                "test", test, Boolean.class, this, pageContext);
            if (r == null)
	        throw new NullAttributeException("when", "test");
            else
                return (((Boolean) r).booleanValue());
	} catch (JspException ex) {
	    throw new JspTagException(ex.toString(), ex);
	}
    }


    //*********************************************************************
    // Private state

    private String test;               // the value of the 'test' attribute


    //*********************************************************************
    // Accessors

    // receives the tag's 'test' attribute
    public void setTest(String test) {
        this.test = test;
    }


    //*********************************************************************
    // Private utility methods

    // resets internal state
    private void init() {
        test = null;
    }
}
