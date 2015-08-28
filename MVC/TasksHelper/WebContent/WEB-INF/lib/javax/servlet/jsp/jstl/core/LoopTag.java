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

package javax.servlet.jsp.jstl.core;

import javax.servlet.jsp.tagext.Tag;

/**
 * <p>JSTL allows developers to write custom iteration tags by
 * implementing the LoopTag interface.  This is not to be confused
 * with <tt>javax.servlet.jsp.tagext.IterationTag</tt> as defined in JSP 1.2.
 * LoopTag establishes a mechanism for iteration tags to be recognized
 * and for type-safe implicit collaboration with custom subtags.
 * 
 * <p>In most cases, it will not be necessary to implement this interface
 * manually, for a base support class (<tt>LoopTagSupport</tt>) is provided
 * to facilitate implementation.</p>
 *
 * @author Shawn Bayern
 */

public interface LoopTag extends Tag {

    /**
     * Retrieves the current item in the iteration.  Behaves
     * idempotently; calling getCurrent() repeatedly should return the same
     * Object until the iteration is advanced.  (Specifically, calling
     * getCurrent() does <b>not</b> advance the iteration.)
     *
     * @return the current item as an object
     */
    public Object getCurrent();

    /**
     * Retrieves a 'status' object to provide information about the
     * current round of the iteration.
     *
     * @return The LoopTagStatus for the current <tt>LoopTag</tt>.
     */
    public LoopTagStatus getLoopStatus();
}
