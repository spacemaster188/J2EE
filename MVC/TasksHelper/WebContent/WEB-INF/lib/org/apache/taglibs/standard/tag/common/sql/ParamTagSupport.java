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
package org.apache.taglibs.standard.tag.common.sql;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.sql.SQLExecutionTag;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.resources.Resources;


/**
 * <p>Tag handler for &lt;Param&gt; in JSTL, used to set
 * parameter values for a SQL statement.</p>
 * 
 * @author Hans Bergsten
 */

public abstract class ParamTagSupport extends BodyTagSupport {
    protected Object value;

    //*********************************************************************
    // Tag logic

    public int doEndTag() throws JspException {
	SQLExecutionTag parent = (SQLExecutionTag) 
	    findAncestorWithClass(this, SQLExecutionTag.class);
	if (parent == null) {
	    throw new JspTagException(
                Resources.getMessage("SQL_PARAM_OUTSIDE_PARENT"));
	}

	Object paramValue = null;
	if (value != null) {
	    paramValue = value;
	}
	else if (bodyContent != null) {
	    paramValue = bodyContent.getString().trim();
	    if (((String) paramValue).trim().length() == 0) {
		paramValue = null;
	    }
	}

	parent.addSQLParameter(paramValue);
	return EVAL_PAGE;
    }
}
