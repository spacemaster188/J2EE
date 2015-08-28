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
package org.apache.taglibs.standard.tag.common.xml;

import javax.xml.xpath.XPathException;

/**
 * <meta name="usage" content="general"/>
 * Derived from XPathException in order that XPath processor
 * exceptions may be specifically caught.
 */
public class UnresolvableException extends XPathException {
    /**
     * Create an UnresolvableException object that holds
     * an error message.
     * @param message The error message.
     */
    public UnresolvableException(String message) {
        super(message);
    }
    
    /**
     * Create an UnresolvableException object that holds
     * an error message, and another exception
     * that caused this exception.
     * @param cause The exception that caused this exception.
     */
    public UnresolvableException(Throwable cause) {
        super(cause);
    }
}
