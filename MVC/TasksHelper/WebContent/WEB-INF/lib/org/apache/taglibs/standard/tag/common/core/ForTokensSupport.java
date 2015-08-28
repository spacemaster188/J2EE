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

import java.util.StringTokenizer;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;
import org.apache.taglibs.standard.resources.Resources;

/**
 * <p>Support for tag handlers for &lt;forTokens&gt;, the tokenizing
 * iteration tag in JSTL 1.0.  This class extends LoopTagSupport and
 * provides ForTokens-specific functionality.  The rtexprvalue and
 * expression-evaluating libraries each have handlers that extend this
 * class.</p>
 *
 * @see javax.servlet.jsp.jstl.core.LoopTagSupport
 * @author Shawn Bayern
 */

public abstract class ForTokensSupport extends LoopTagSupport {

    //*********************************************************************
    // Implementation overview

    /*
     * This handler simply constructs a StringTokenizer based on its input
     * and relays tokens to the iteration implementation that it inherits.
     * The 'items' and 'delims' attributes are expected to be provided by
     * a subtag (presumably in the rtexprvalue or expression-evaluating
     * versions of the JSTL library).
     */


    //*********************************************************************
    // ForEachTokens-specific state (protected)

    protected Object items;                       // 'items' attribute
    protected String delims;                      // 'delims' attribute
    protected StringTokenizer st;                 // digested tokenizer


    //*********************************************************************
    // Iteration control methods

    /*
     * These just create and use a StringTokenizer.
     * We inherit semantics and Javadoc from LoopTagSupport.
     */

    protected void prepare() throws JspTagException {
        if (items instanceof ValueExpression) {
            deferredExpression = (ValueExpression) items;
            items = deferredExpression.getValue(pageContext.getELContext());
        }
        if (!(items instanceof String)) {
            throw new JspTagException(
                Resources.getMessage("FORTOKENS_BAD_ITEMS"));
        }
        st = new StringTokenizer((String)items, delims);
    }

    protected boolean hasNext() throws JspTagException {
        return st.hasMoreElements();
    }

    protected Object next() throws JspTagException {
        return st.nextElement();
    }

    protected String getDelims() {
        return delims;
    }

    //*********************************************************************
    // Tag logic and lifecycle management


    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        items = delims = null;
        st = null;
        deferredExpression = null;
    }

}
