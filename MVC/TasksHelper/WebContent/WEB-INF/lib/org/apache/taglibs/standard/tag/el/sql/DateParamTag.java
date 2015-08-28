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
package org.apache.taglibs.standard.tag.el.sql;

import java.util.Date;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.sql.DateParamTagSupport;

/**
 * Subclass for the JSTL library with EL support.
 *
 * @author Justyna Horwat
 */
public class DateParamTag extends DateParamTagSupport {
    
    private String valueEL;
    private String typeEL;

    public void setValue(String valueEL) {
	this.valueEL = valueEL;
    }

    public void setType(String typeEL) {
	this.typeEL = typeEL;
    }

    public int doStartTag() throws JspException {
        evaluateExpressions();
	return super.doStartTag();
    }

    //*********************************************************************
    // Private utility methods

    // Evaluates expressions as necessary
    private void evaluateExpressions() throws JspException {
	if (valueEL != null) {
	    value = (Date) ExpressionEvaluatorManager.evaluate(
                "value", valueEL, Date.class, this, pageContext);
	}

	if (typeEL != null) {
	    type = (String) ExpressionEvaluatorManager.evaluate(
                "type", typeEL, String.class, this, pageContext);
        }
    }

}
