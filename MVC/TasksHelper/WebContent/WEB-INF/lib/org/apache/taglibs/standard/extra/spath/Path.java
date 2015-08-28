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

package org.apache.taglibs.standard.extra.spath;

import java.util.List;

/**
 * <p>Represents a simple path (SPath) expression.  A path is an ordered
 * list of Steps.
 *
 * @author Shawn Bayern
 */
public abstract class Path {

    /**
     * Retrives an ordered list of Step objects representing this
     * expression.  The result is safely modifiable by the caller and
     * must support List.add(Object) and List.add(int, Object).
     */
    public abstract List getSteps();

}
