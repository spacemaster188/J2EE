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

package javax.servlet.jsp.jstl.sql;

/**
 * <p>This interface allows tag handlers implementing it to receive
 * values for parameter markers in their SQL statements.</p>
 *
 * <p>This interface is implemented by both &lt;sql:query&gt; and
 * &lt;sql:update&gt;. Its <code>addSQLParameter()</code> method
 * is called by nested parameter actions (such as &lt;sql:param&gt;)
 * to substitute <code>PreparedStatement</code> parameter values for
 * "?" parameter markers in the SQL statement of the enclosing
 * <code>SQLExecutionTag</code> action.</p>
 *
 * <p>The given parameter values are converted to their corresponding
 * SQL type (following the rules in the JDBC specification) before
 * they are sent to the database.</p>
 *
 * <p>Keeping track of the index of the parameter values being added
 * is the responsibility of the tag handler implementing this
 * interface</p>
 *
 * <p>The <code>SQLExcecutionTag</code> interface is exposed in order
 * to support custom parameter actions which may retrieve their
 * parameters from any source and process them before substituting
 * them for a parameter marker in the SQL statement of the
 * enclosing <code>SQLExecutionTag</code> action</p>
 *
 * @author Justyna Horwat
 */
public interface SQLExecutionTag {

    /**
     * Adds a PreparedStatement parameter value. 
     * Must behave as if it calls <code>PreparedStatement.setObject(int, Object)</code>. 
     * For each tag invocation, the integral index passed logically to <code>setObject()</code> 
     * must begin with 1 and must be incremented by 1 for each subsequent invocation 
     * of <code>addSQLParameter()</code>. The Object logically passed to <code>setObject()</code> must be the 
     * unmodified object received in the value argument.
     *
     * @param value the <code>PreparedStatement</code> parameter value
     */
    public void addSQLParameter(Object value);
}
