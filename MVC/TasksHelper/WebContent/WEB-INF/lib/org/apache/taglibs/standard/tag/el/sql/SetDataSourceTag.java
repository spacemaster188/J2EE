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
import org.apache.taglibs.standard.tag.common.sql.SetDataSourceTagSupport;

/**
 * <p>Tag handler for &lt;SetDataSource&gt; in JSTL, used to create
 * a simple DataSource for prototyping.</p>
 * 
 */
public class SetDataSourceTag extends SetDataSourceTagSupport {

    private String dataSourceEL;
    private String driverClassNameEL;
    private String jdbcURLEL;
    private String userNameEL;
    private String passwordEL;

    //*********************************************************************
    // Accessor methods

    public void setDataSource(String dataSourceEL) {
	this.dataSourceEL = dataSourceEL;
	this.dataSourceSpecified = true;
    }

    public void setDriver(String driverClassNameEL) {
	this.driverClassNameEL = driverClassNameEL;
    }

    public void setUrl(String jdbcURLEL) {
	this.jdbcURLEL = jdbcURLEL;
    }

    public void setUser(String userNameEL) {
	this.userNameEL = userNameEL;
    }

    public void setPassword(String passwordEL) {
	this.passwordEL = passwordEL;
    }

    //*********************************************************************
    // Tag logic

    public int doStartTag() throws JspException {
        evaluateExpressions();

        return super.doStartTag();
    }


    //*********************************************************************
    // Private utility methods

    // Evaluates expressions as necessary
    private void evaluateExpressions() throws JspException {
        if (dataSourceEL != null) {
                dataSource = ExpressionEvaluatorManager.evaluate
                ("dataSource", dataSourceEL, Object.class, this, pageContext);
        }

        if (driverClassNameEL != null) {
                driverClassName = (String) ExpressionEvaluatorManager.evaluate
                ("driver", driverClassNameEL, String.class, this, pageContext);
        }

        if (jdbcURLEL != null) {
                jdbcURL = (String) ExpressionEvaluatorManager.evaluate
                ("url", jdbcURLEL, String.class, this, pageContext);
        }

        if (userNameEL != null) {
                userName = (String) ExpressionEvaluatorManager.evaluate
                ("user", userNameEL, String.class, this, pageContext);
        }

        if (passwordEL != null) {
                password = (String) ExpressionEvaluatorManager.evaluate
                ("password", passwordEL, String.class, this, pageContext);
        }
    }

}
