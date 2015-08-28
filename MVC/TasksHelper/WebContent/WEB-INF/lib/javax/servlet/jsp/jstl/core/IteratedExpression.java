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
 */

package javax.servlet.jsp.jstl.core;

import java.util.Iterator;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ValueExpression;

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.resources.Resources;

/**
 * @author Kin-man Chung
 * @version $Id: IteratedExpression.java,v 1.3 2005/12/08 01:20:43 kchung Exp $
 */
public final class IteratedExpression {

    private static final long serialVersionUID = 1L;
    protected final ValueExpression orig;
    protected final String delims;

    private Object base;
    private int index;
    private Iterator iter;

    public IteratedExpression(ValueExpression orig, String delims) {
        this.orig = orig;
        this.delims = delims;
    }

    /**
     * Evaluates the stored ValueExpression and return the indexed item.
     * @param context The ELContext used to evaluate the ValueExpression
     * @param i The index of the item to be retrieved
     */
    public Object getItem(ELContext context, int i) {

        if (base == null) {
            base = orig.getValue(context);
            if (base == null) {
                return null;
            }
            iter = toIterator(base);
            index = 0;
        }
        if (index > i) {
            // Restart from index 0
            iter = toIterator(base);
            index = 0;
        }
        while (iter.hasNext()) {
            Object item = iter.next();
            if (index++ == i) {
                return item;
            }
        }
        return null;
    }

    public ValueExpression getValueExpression() {
        return orig;
    }

    private Iterator toIterator(final Object obj) {

        Iterator iter;
        if (obj instanceof String) {
            iter = toIterator(new StringTokenizer((String)obj, delims));
        }
        else if (obj instanceof Iterator) {
            iter = (Iterator)obj;
        }
        else if (obj instanceof Collection) {
            iter = toIterator(((Collection) obj).iterator());
        }
        else if (obj instanceof Enumeration) {
            iter = toIterator((Enumeration)obj);
        }
        else if (obj instanceof Map) {
            iter = ((Map)obj).entrySet().iterator();
        } else {
            throw new ELException(Resources.getMessage("FOREACH_BAD_ITEMS"));
        }
        return iter;
    }

    private Iterator toIterator(final Enumeration obj) {
        return new Iterator() {
            public boolean hasNext() {
                return obj.hasMoreElements();
            }
            public Object next() {
                return obj.nextElement();
            }
            public void remove() {}
        };
    }
}

