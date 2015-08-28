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

package org.apache.taglibs.standard.tag.common.xml;

import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import org.apache.taglibs.standard.resources.Resources;

/**
 * <p>Support for the XML library's &lt;forEach&gt; tag.</p>
 *
 * @see javax.servlet.jsp.jstl.core.LoopTagSupport
 * @author Shawn Bayern
 */
public class ForEachTag extends LoopTagSupport {

    //*********************************************************************
    // Private state

    private String select;				// tag attribute
    private List nodes;					// XPath result
    private int nodesIndex;				// current index
    private org.w3c.dom.Node current;			// current node

    //*********************************************************************
    // Iteration control methods

    // (We inherit semantics and Javadoc from LoopTagSupport.) 

    protected void prepare() throws JspTagException {
        nodesIndex = 0;
        XPathUtil xu = new XPathUtil(pageContext);
        nodes = xu.selectNodes(XPathUtil.getContext(this), select);
    }

    protected boolean hasNext() throws JspTagException {
        return (nodesIndex < nodes.size());
    }

    protected Object next() throws JspTagException {
	Object o = nodes.get(nodesIndex++);
	if (!(o instanceof org.w3c.dom.Node))
	    throw new JspTagException(
		Resources.getMessage("FOREACH_NOT_NODESET"));
	current = (org.w3c.dom.Node) o;
        return current;
    }


    //*********************************************************************
    // Tag logic and lifecycle management

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
        super.release();
    }


    //*********************************************************************
    // Attribute accessors

    public void setSelect(String select) {
	this.select = select;
    }

    public void setBegin(int begin) throws JspTagException {
        this.beginSpecified = true;
        this.begin = begin;
        validateBegin();
    }

    public void setEnd(int end) throws JspTagException {
        this.endSpecified = true;
        this.end = end;
        validateEnd();
    }

    public void setStep(int step) throws JspTagException {
        this.stepSpecified = true;
        this.step = step;
        validateStep();
    }
    
    //*********************************************************************
    // Public methods for subtags

    /* Retrieves the current context. */
    public org.w3c.dom.Node getContext() throws JspTagException {
	// expose the current node as the context
        return current;
    }


    //*********************************************************************
    // Private utility methods

    private void init() {
	select = null;
	nodes = null;
	nodesIndex = 0;
	current = null;
    }	
}

