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

package org.apache.taglibs.standard.tag.rt.core;

import org.apache.taglibs.standard.tag.common.core.OutSupport;

/**
 * <p>Tag handler for &lt;out&gt; in JSTL's rtexprvalue library.</p>
 *
 * @author Shawn Bayern
 */

public class OutTag extends OutSupport {

    //*********************************************************************
    // Accessors
       
    // for tag attribute
    public void setValue(Object value) {
        this.value = value;
    }
      
    // for tag attribute
    public void setDefault(String def) {
        this.def = def;
    }
        
    // for tag attribute
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }
}
