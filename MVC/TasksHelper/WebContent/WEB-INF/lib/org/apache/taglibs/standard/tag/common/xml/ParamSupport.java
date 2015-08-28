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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.taglibs.standard.resources.Resources;

/**
 * <p>Support for tag handlers for &lt;param&gt;, the XML parameter
 * subtag for &lt;transformt&lt;.</p>
 *
 * @see TransformSupport
 * @author Shawn Bayern
 */

public abstract class ParamSupport extends BodyTagSupport {

    //*********************************************************************
    // Protected state

    protected String name;                       // 'name' attribute
    protected Object value;                      // 'value' attribute

    //*********************************************************************
    // Constructor and initialization

    public ParamSupport() {
	super();
	init();
    }

    private void init() {
	name = null;
	value = null;
    }


    //*********************************************************************
    // Tag logic

    // simply send our name and value to our parent <transform> tag
    public int doEndTag() throws JspException {
	Tag t = findAncestorWithClass(this, TransformSupport.class);
	if (t == null)
	    throw new JspTagException(
		Resources.getMessage("PARAM_OUTSIDE_TRANSFORM"));
	TransformSupport parent = (TransformSupport) t;

	Object value = this.value;
	if (value == null) {
            if (bodyContent == null || bodyContent.getString() == null)
                value = "";
            else
                value = bodyContent.getString().trim();
        }
	parent.addParameter(name, value);
	return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
