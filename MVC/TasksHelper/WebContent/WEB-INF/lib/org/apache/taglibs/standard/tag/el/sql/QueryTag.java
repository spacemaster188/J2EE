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

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.sql.QueryTagSupport;

/**
 * Subclass for the JSTL library with EL support.
 *
 * @author Hans Bergsten
 * @author Justyna Horwat
 */
public class QueryTag extends QueryTagSupport {

    private String dataSourceEL;
    private String sqlEL;
    private String startRowEL;
    private String maxRowsEL;

    //*********************************************************************
    // Constructor

    /**
     * Constructs a new QueryTag.  As with TagSupport, subclasses
     * should not provide other constructors and are expected to call
     * the superclass constructor
     */
    public QueryTag() {
        super();
    }

    //*********************************************************************
    // Accessor methods

    public void setDataSource(String dataSourceEL) {
	this.dataSourceEL = dataSourceEL;
	this.dataSourceSpecified = true;
    }

    /**
     * The index of the first row returned can be
     * specified using startRow.
     */
    public void setStartRow(String startRowEL) {
	this.startRowEL = startRowEL;
    }

    /**
     * Query result can be limited by specifying
     * the maximum number of rows returned.
     */
    public void setMaxRows(String maxRowsEL) {
	this.maxRowsEL = maxRowsEL;
	this.maxRowsSpecified = true;
    }

    /**
     * Setter method for the SQL statement to use for the
     * query. The statement may contain parameter markers
     * (question marks, ?). If so, the parameter values must
     * be set using nested value elements.
     */
    public void setSql(String sqlEL) {
	this.sqlEL = sqlEL;
    }

    public int doStartTag() throws JspException {
        evaluateExpressions();
	return super.doStartTag();
    }

    //*********************************************************************
    // Private utility methods

    // Evaluates expressions as necessary
    private void evaluateExpressions() throws JspException {
        Integer tempInt = null;

        if (dataSourceEL != null) {
            rawDataSource = (Object) ExpressionEvaluatorManager.evaluate(
                "dataSource", dataSourceEL, Object.class, this, pageContext);
        }

        if (sqlEL != null) {
            sql = (String) ExpressionEvaluatorManager.evaluate("sql", sqlEL,
                String.class, this, pageContext);
        }

	if (startRowEL != null) {
	    tempInt = (Integer) ExpressionEvaluatorManager.evaluate(
                "startRow", startRowEL, Integer.class, this, pageContext);
	    if (tempInt != null)
		startRow = tempInt.intValue();
	}

	if (maxRowsEL != null) {
	    tempInt = (Integer) ExpressionEvaluatorManager.evaluate(
                "maxRows", maxRowsEL, Integer.class, this, pageContext);
	    if (tempInt != null)
		maxRows = tempInt.intValue();
	}
    }
}
