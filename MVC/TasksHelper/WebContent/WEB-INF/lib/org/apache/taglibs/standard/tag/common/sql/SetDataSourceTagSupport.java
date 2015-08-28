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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.tagext.TagSupport;
import javax.sql.DataSource;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.Util;


/**
 * <p>Tag handler for &lt;SetDataSource&gt; in JSTL, used to create
 * a simple DataSource for prototyping.</p>
 * 
 * @author Hans Bergsten
 * @author Justyna Horwat
 */
public class SetDataSourceTagSupport extends TagSupport {

    protected Object dataSource;
    protected boolean dataSourceSpecified;
    protected String jdbcURL;
    protected String driverClassName;
    protected String userName;
    protected String password;

    private int scope;
    private String var;


    //*********************************************************************
    // Constructor and initialization

    public SetDataSourceTagSupport() {
	super();
	init();
    }

    private void init() {
	dataSource = null;
	dataSourceSpecified = false;
	jdbcURL = driverClassName = userName = password = null;
	var = null;
	scope = PageContext.PAGE_SCOPE;
    }


    //*********************************************************************
    // Accessor methods

    /**
     * Setter method for the scope of the variable to hold the
     * result.
     *
     */
    public void setScope(String scope) {
        this.scope = Util.getScope(scope);
    }

    public void setVar(String var) {
	this.var = var;
    }


    //*********************************************************************
    // Tag logic

    public int doStartTag() throws JspException {
        DataSource ds;

        if (dataSource != null) {
            ds = DataSourceUtil.getDataSource(dataSource, pageContext);
        } else {
	    if (dataSourceSpecified) {
		throw new JspException(
                    Resources.getMessage("SQL_DATASOURCE_NULL"));
	    }

            DataSourceWrapper dsw = new DataSourceWrapper();
            try {
                // set driver class iff provided by the tag
                if (driverClassName != null) {
                    dsw.setDriverClassName(driverClassName);
                }
            }
            catch (Exception e) {
                throw new JspTagException(
                    Resources.getMessage("DRIVER_INVALID_CLASS",
					 e.toString()), e);
            }
            dsw.setJdbcURL(jdbcURL);
            dsw.setUserName(userName);
            dsw.setPassword(password);
	    ds = (DataSource) dsw;
        }

        if (var != null) {
	    pageContext.setAttribute(var, ds, scope);
        } else {
            Config.set(pageContext, Config.SQL_DATA_SOURCE, ds, scope);
        }

	return SKIP_BODY;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
