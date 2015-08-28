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

package javax.servlet.jsp.jstl.core;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Abstract class that facilitates implementation of conditional actions 
 * where the boolean result is exposed as a JSP scoped variable. The 
 * boolean result may then be used as the test condition in a &lt;c:when&gt;
 * action.</p>
 *
 * <p>This base class provides support for:</p>
 * 
 * <ul>
 *  <li> Conditional processing of the action's body based on the returned value
 *       of the abstract method <tt>condition()</tt>.</li>
 *  <li> Storing the result of <tt>condition()</tt> as a <tt>Boolean</tt> object
 *       into a JSP scoped variable identified by attributes <tt>var</tt> and
 *       <tt>scope</tt>.
 * </ul>
 * 
 * @author Shawn Bayern
 */

public abstract class ConditionalTagSupport
    extends TagSupport
{
    //*********************************************************************
    // Abstract methods

    /**
     * <p>Subclasses implement this method to compute the boolean result
     * of the conditional action. This method is invoked once per tag invocation 
     * by <tt>doStartTag()</tt>.
     *
     * @return a boolean representing the condition that a particular subclass
     *   uses to drive its conditional logic.
     */
    protected abstract boolean condition() throws JspTagException;


    //*********************************************************************
    // Constructor

    /**
     * Base constructor to initialize local state.  As with <tt>TagSupport</tt>,
     * subclasses should not implement constructors with arguments, and
     * no-argument constructors implemented by subclasses must call the 
     * superclass constructor.
     */
    public ConditionalTagSupport() {
        super();
        init();
    }


    //*********************************************************************
    // Lifecycle management and implementation of conditional behavior

    /**
     * Includes its body if <tt>condition()</tt> evaluates to true.
     */
    public int doStartTag() throws JspException {

        // execute our condition() method once per invocation
        result = condition();

        // expose variables if appropriate
        exposeVariables();

        // handle conditional behavior
        if (result)
            return EVAL_BODY_INCLUDE;
        else
            return SKIP_BODY;
    }

    /**
     * Releases any resources this ConditionalTagSupport may have (or inherit).
     */
    public void release() {
        super.release();
        init();
    }

    //*********************************************************************
    // Private state

    private boolean result;             // the saved result of condition()
    private String var;			// scoped attribute name
    private int scope;			// scoped attribute scope


    //*********************************************************************
    // Accessors

    /**
     * Sets the 'var' attribute.
     *
     * @param var Name of the exported scoped variable storing the result of
     * <tt>condition()</tt>.
     */
    public void setVar(String var) {
	this.var = var;
    }

    /**
     * Sets the 'scope' attribute.
     *
     * @param scope Scope of the 'var' attribute
     */
    public void setScope(String scope) {
	if (scope.equalsIgnoreCase("page"))
	    this.scope = PageContext.PAGE_SCOPE;
	else if (scope.equalsIgnoreCase("request"))
	    this.scope = PageContext.REQUEST_SCOPE;
	else if (scope.equalsIgnoreCase("session"))
	    this.scope = PageContext.SESSION_SCOPE;
	else if (scope.equalsIgnoreCase("application"))
	    this.scope = PageContext.APPLICATION_SCOPE;
	// TODO: Add error handling?  Needs direction from spec.
    }


    //*********************************************************************
    // Utility methods

    // expose attributes if we have a non-null 'var'
    private void exposeVariables() {
        if (var != null)
            pageContext.setAttribute(var, new Boolean(result), scope);
    }

    // initializes internal state
    private void init() {
        result = false;                 // not really necessary
	var = null;
	scope = PageContext.PAGE_SCOPE;
    }
}
