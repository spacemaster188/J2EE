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

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.taglibs.standard.resources.Resources;


/**
 * <p>A simple <code>DataSource</code> wrapper for the standard
 * <code>DriverManager</code> class.
 * 
 * @author Hans Bergsten
 */
public class DataSourceWrapper implements DataSource {
    private String driverClassName;
    private String jdbcURL;
    private String userName;
    private String password;

    public void setDriverClassName(String driverClassName) 
	throws ClassNotFoundException, InstantiationException, 
	       IllegalAccessException {

	this.driverClassName = driverClassName;
        Class.forName(driverClassName, true, 
            Thread.currentThread().getContextClassLoader()).newInstance();
    }

    public void setJdbcURL(String jdbcURL) {
	this.jdbcURL = jdbcURL;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Returns a Connection using the DriverManager and all
     * set properties.
     */
    public Connection getConnection() throws SQLException {
	Connection conn = null;
	if (userName != null) {
	    conn = DriverManager.getConnection(jdbcURL, userName, password);
	}
	else {
	    conn = DriverManager.getConnection(jdbcURL);
	}
	return conn;
    }

    /**
     * Always throws a SQLException. Username and password are set
     * in the constructor and can not be changed.
     */
    public Connection getConnection(String username, String password) 
            throws SQLException {
        throw new SQLException(Resources.getMessage("NOT_SUPPORTED"));
    }
    
    /**
     * Always throws a SQLException. Not supported.
     */
    public int getLoginTimeout() throws SQLException {
        throw new SQLException(Resources.getMessage("NOT_SUPPORTED"));
    }
    
    /**
     * Always throws a SQLException. Not supported.
     */
    public PrintWriter getLogWriter() throws SQLException {
        throw new SQLException(Resources.getMessage("NOT_SUPPORTED"));
    }
    
    /**
     * Always throws a SQLException. Not supported.
     */
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new SQLException(Resources.getMessage("NOT_SUPPORTED"));
    }
    
    /**
     * Always throws a SQLException. Not supported.
     */
    public synchronized void setLogWriter(PrintWriter out) throws SQLException {
        throw new SQLException(Resources.getMessage("NOT_SUPPORTED"));
    }


}
