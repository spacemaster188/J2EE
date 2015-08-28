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


/**
 * <p>Represents a predicate expression concerning a single attribute.</p>
 *
 * @author Shawn Bayern
 */
public class AttributePredicate extends Predicate {

    private String attribute, target;

    /**
     * Constructs a new AttributePredicate, given an attribute name
     * and a target literal (with which to test equality).
     */
    public AttributePredicate(String attribute, String target) {
	if (attribute == null)
	    throw new IllegalArgumentException("non-null attribute needed");
	if (attribute.indexOf(":") != -1)
	    throw new IllegalArgumentException(
		"namespace-qualified attribute names are not currently " +
		"supported");
	this.attribute = attribute;

	if (target == null)
	    throw new IllegalArgumentException("non-null target needed");
	// strip quotation marks from target
	this.target = target.substring(1, target.length() - 1);
    }

    /**
     * Returns true if the given SAX AttributeList is suitable, given our
     * attribute name and target; returns false otherwise.
     */
    public boolean isMatchingAttribute(org.xml.sax.Attributes a) {
	String attValue = a.getValue("", attribute);
	return (attValue != null && attValue.equals(target));
    }
} 
