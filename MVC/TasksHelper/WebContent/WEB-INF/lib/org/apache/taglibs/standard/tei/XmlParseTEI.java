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

package org.apache.taglibs.standard.tei;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

/**
 * <p>An implementation of TagExtraInfo that implements validation for
 * <x:parse>'s attributes</p>
 *
 * @author Shawn Bayern
 */
public class XmlParseTEI extends TagExtraInfo {

    final private static String VAR = "var";
    final private static String VAR_DOM = "varDom";
    final private static String SCOPE = "scope";
    final private static String SCOPE_DOM = "scopeDom";

    public boolean isValid(TagData us) {
	// must have no more than one of VAR and VAR_DOM ...
	if (Util.isSpecified(us, VAR) && Util.isSpecified(us, VAR_DOM))
	    return false;

	// ... and must have no less than one of VAR and VAR_DOM
	if (!(Util.isSpecified(us, VAR) || Util.isSpecified(us, VAR_DOM)))
	    return false;

	// When either 'scope' is specified, its 'var' must be specified
	if (Util.isSpecified(us, SCOPE) && !Util.isSpecified(us, VAR))
	    return false;
	if (Util.isSpecified(us, SCOPE_DOM) && !Util.isSpecified(us, VAR_DOM))
	    return false;

        return true;
    }

}
