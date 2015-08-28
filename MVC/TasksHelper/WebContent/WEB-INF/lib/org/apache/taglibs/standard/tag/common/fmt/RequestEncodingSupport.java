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

package org.apache.taglibs.standard.tag.common.fmt;

import java.io.UnsupportedEncodingException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Support for tag handlers for &lt;requestEncoding&gt;, the tag for setting
 * the request character encoding in JSTL 1.0.
 *
 * @author Jan Luehe
 * @author Pierre Delisle
 */

public abstract class RequestEncodingSupport extends TagSupport {

    //*********************************************************************
    // Package-scoped constants

    static final String REQUEST_CHAR_SET =
	"javax.servlet.jsp.jstl.fmt.request.charset";


    //*********************************************************************
    // Private constants

    private static final String DEFAULT_ENCODING = "ISO-8859-1";


    //*********************************************************************
    // Tag attributes

    protected String value;             // 'value' attribute
    

    //*********************************************************************
    // Derived information
    
    protected String charEncoding;   // derived from 'value' attribute  
    

    //*********************************************************************
    // Constructor and initialization

    public RequestEncodingSupport() {
	super();
	init();
    }

    private void init() {
	value = null;
    }


    //*********************************************************************
    // Tag logic

    public int doEndTag() throws JspException {
        charEncoding = value;
	if ((charEncoding == null)
	        && (pageContext.getRequest().getCharacterEncoding() == null)) { 
            // Use charset from session-scoped attribute
	    charEncoding = (String)
		pageContext.getAttribute(REQUEST_CHAR_SET,
					 PageContext.SESSION_SCOPE);
	    if (charEncoding == null) {
		// Use default encoding
		charEncoding = DEFAULT_ENCODING;
	    }
	}

	/*
	 * If char encoding was already set in the request, we don't need to 
	 * set it again.
	 */
	if (charEncoding != null) {
	    try {
		pageContext.getRequest().setCharacterEncoding(charEncoding);
	    } catch (UnsupportedEncodingException uee) {
		throw new JspTagException(uee.toString(), uee);
	    }
	}

	return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
