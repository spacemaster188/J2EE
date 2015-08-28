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
import java.util.Vector;

/**
 * <p>Represents a relative SPath expression.</p>
 *
 * @author Shawn Bayern
 */
public class RelativePath extends Path {

    private RelativePath next;
    private Step step;

    /**
     * Constructs a new RelativePath object, based on a Step and another
     * (possibly null) RelativePath.  If 'all' is true, then the path
     * matches all instances of 'next' underneath 'step'; otherwise;
     * 'next' must be an immediate child of 'step'.
     */
    public RelativePath(Step step, RelativePath next) {
	if (step == null)
	    throw new IllegalArgumentException("non-null step required");
	this.step = step;
	this.next = next;
    }

    // inherit JavaDoc comment
    public List getSteps() {
	// simply merge our 'step' with our 'next'
	List l;
	if (next != null)
	    l = next.getSteps();
	else
	    l = new Vector();
	l.add(0, step);
	return l;
    }
}
