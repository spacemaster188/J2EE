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

package javax.servlet.jsp.jstl.tlv;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.jsp.tagext.PageData;
import javax.servlet.jsp.tagext.TagLibraryValidator;
import javax.servlet.jsp.tagext.ValidationMessage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p>A TagLibraryValidator class to allow a TLD to restrict what
 * taglibs (in addition to itself) may be imported on a page where it's
 * used.</p>
 *
 * <p>This TLV supports the following initialization parameter:</p>
 * <ul>
 * <li><b>permittedTaglibs</b>: A whitespace-separated list of URIs corresponding 
 *   to tag libraries permitted to be imported on the page in addition to the tag 
 *   library that references PermittedTaglibsTLV (which is allowed implicitly).
 * </ul>
 *
 * @author Shawn Bayern
 */
public class PermittedTaglibsTLV extends TagLibraryValidator {

    //*********************************************************************
    // Constants

    // parameter names
    private final String PERMITTED_TAGLIBS_PARAM = "permittedTaglibs";

    // URI for "<jsp:root>" element
    private final String JSP_ROOT_URI = "http://java.sun.com/JSP/Page";

    // local name of "<jsp:root>" element
    private final String JSP_ROOT_NAME = "root";

    // QName for "<jsp:root>" element
    private final String JSP_ROOT_QN = "jsp:root";


    //*********************************************************************
    // Validation and configuration state (protected)

    private Set permittedTaglibs;		// what URIs are allowed?
    private boolean failed;			// did the page fail?
    private String uri;				// our taglib's URI

    //*********************************************************************
    // Constructor and lifecycle management

    public PermittedTaglibsTLV() {
	super();
	init();
    }

    private void init() {
	permittedTaglibs = null;
    }

    public void release() {
	super.release();
	init();
    }
    

    //*********************************************************************
    // Validation entry point

    public synchronized ValidationMessage[] validate(
	    String prefix, String uri, PageData page) {
	try {

	    // initialize
	    this.uri = uri;
	    permittedTaglibs = readConfiguration();

	    // get a handler
	    DefaultHandler h = new PermittedTaglibsHandler();

	    // parse the page
	    SAXParserFactory f = SAXParserFactory.newInstance();
	    f.setValidating(true);
	    SAXParser p = f.newSAXParser();
	    p.parse(page.getInputStream(), h);

	    if (failed)
		return vmFromString(
		    "taglib " + prefix + " (" + uri + ") allows only the "
		    + "following taglibs to be imported: " + permittedTaglibs);
	    else
		return null;

	} catch (SAXException ex) {
	    return vmFromString(ex.toString());
	} catch (ParserConfigurationException ex) {
	    return vmFromString(ex.toString());
	} catch (IOException ex) {
	    return vmFromString(ex.toString());
	}
    }


    //*********************************************************************
    // Utility functions

    /** Returns Set of permitted taglibs, based on configuration data. */
    private Set readConfiguration() {

	// initialize the Set
	Set s = new HashSet();

	// get the space-separated list of taglibs
	String uris = (String) getInitParameters().get(PERMITTED_TAGLIBS_PARAM);

        // separate the list into individual uris and store them
        StringTokenizer st = new StringTokenizer(uris);
        while (st.hasMoreTokens())
	    s.add(st.nextToken());

	// return the new Set
	return s;

    }

    // constructs a ValidationMessage[] from a single String and no ID
    private ValidationMessage[] vmFromString(String message) {
	return new ValidationMessage[] {
	    new ValidationMessage(null, message)
	};
    }


    //*********************************************************************
    // SAX handler

    /** The handler that provides the base of our implementation. */
    private class PermittedTaglibsHandler extends DefaultHandler {

        // if the element is <jsp:root>, check its "xmlns:" attributes
        public void startElement(
                String ns, String ln, String qn, Attributes a) {

	    // ignore all but <jsp:root>
	    if (!qn.equals(JSP_ROOT_QN) &&
	            (!ns.equals(JSP_ROOT_URI) || !ln.equals(JSP_ROOT_NAME)))
		return;

	    // for <jsp:root>, check the attributes
	    for (int i = 0; i < a.getLength(); i++) {
		String name = a.getQName(i);

		// ignore non-namespace attributes, and xmlns:jsp
		if (!name.startsWith("xmlns:") || name.equals("xmlns:jsp"))
		    continue;

		String value = a.getValue(i);
		// ignore our own namespace declaration
		if (value.equals(uri))
		    continue;

		// otherwise, ensure that 'value' is in 'permittedTaglibs' set
		if (!permittedTaglibs.contains(value))
		    failed = true;
	    }
	}
    }

}
