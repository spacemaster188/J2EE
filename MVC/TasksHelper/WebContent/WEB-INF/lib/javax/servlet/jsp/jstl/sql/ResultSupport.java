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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Supports the creation of a javax.servlet.jsp.jstl.sql.Result object 
 * from a source java.sql.ResultSet object. A Result object makes it much 
 * easier for page authors to access and manipulate the data resulting 
 * from a SQL query.</p>
 *
 * @author Justyna Horwat
 *
 */
public class ResultSupport {


    /**
     * Converts a <code>ResultSet</code> object to a <code>Result</code> object.
     *
     * @param rs the <code>ResultSet</code> object
     *
     * @return The <code>Result</code> object created from the <code>ResultSet</code>
     */
    public static Result toResult(ResultSet rs) {
        try {
            return new ResultImpl(rs, -1, -1);
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     * Converts <code>maxRows</code> of a <code>ResultSet</code> object to a 
     * <code>Result</code> object.
     *
     * @param rs the <code>ResultSet</code> object
     * @param maxRows the maximum number of rows to be cached into the <code>Result</code> object.
     *
     * @return The <code>Result</code> object created from the <code>ResultSet</code>,
     * limited by <code>maxRows</code>
     */
    public static Result toResult(ResultSet rs, int maxRows) {
        try {
            return new ResultImpl(rs, -1, maxRows);
        } catch (SQLException ex) {
            return null;
        }
    }

}
