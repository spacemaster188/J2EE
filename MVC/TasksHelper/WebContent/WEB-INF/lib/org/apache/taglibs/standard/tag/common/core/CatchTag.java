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

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

/**
 * <p>Tag handler for &lt;catch&gt; in JSTL 1.0.</p>
 * 
 * <p>&lt;catch&gt; simply catches any Throwables that occur in its body
 * and optionally exposes them.
 *
 * @author Shawn Bayern
 */

public class CatchTag extends TagSupport implements TryCatchFinally {

    /*
     * If all tags that I proposed were this simple, people might
     * think I was just trying to avoid work.  :-)
     */

    //*********************************************************************
    // Constructor and lifecycle management

    // initialize inherited and local state
    public CatchTag() {
        super();
        init();
    }

    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }

    private void init() {
        var = null;
    }


    //*********************************************************************
    // Private state

    private String var;                                 // tag attribute
    private boolean caught;                             // internal status


    //*********************************************************************
    // Tag logic

    public int doStartTag() {
        caught = false;
	return EVAL_BODY_INCLUDE;
    }

    public void doCatch(Throwable t) {
        if (var != null)
            pageContext.setAttribute(var, t, PageContext.PAGE_SCOPE);
        caught = true;
    }

    public void doFinally() {
        if (var != null && !caught)
            pageContext.removeAttribute(var, PageContext.PAGE_SCOPE);
    }


    //*********************************************************************
    // Attribute accessors

    public void setVar(String var) {
        this.var = var;
    }

}
