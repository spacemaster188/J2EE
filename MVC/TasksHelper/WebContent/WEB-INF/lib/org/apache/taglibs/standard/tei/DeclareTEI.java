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
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * <p>An implementation of TagExtraInfo provided for &lt;declare&gt;.
 * We simply set up a scripting variable for the ID and value that
 * &lt;declare&gt; already stored.  For EA2, DefineTEI *always* declares
 * the variable; no option is given via a tag attribute.  Visibility is
 * always AT_END.</p>
 *
 * @author Shawn Bayern
 */
public class DeclareTEI extends TagExtraInfo {

    // purposely inherit JavaDoc and semantics from TagExtraInfo
    public VariableInfo[] getVariableInfo(TagData data) {
        // construct the relevant VariableInfo object
        VariableInfo id = new VariableInfo(
            data.getAttributeString("id"),
            data.getAttributeString("type") == null ?
		"java.lang.Object" : data.getAttributeString("type"),
            true,
            VariableInfo.AT_END);
        return new VariableInfo[] { id };
    }
}
