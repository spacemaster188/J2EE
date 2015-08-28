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

import javax.el.ELContext;
import javax.el.ValueExpression;

/**
 * @author Kin-man Chung
 * @version $Id: IteratedValueExpression.java,v 1.2 2005/12/08 01:20:43 kchung Exp $
 */
public final class IteratedValueExpression extends ValueExpression {

    private static final long serialVersionUID = 1L;
    protected final int i;
    protected final IteratedExpression iteratedExpression;

    public IteratedValueExpression(IteratedExpression iteratedExpr, int i) {
        this.i = i;
        this.iteratedExpression = iteratedExpr;
    }

    public Object getValue(ELContext context) {
        return iteratedExpression.getItem(context, i);
    }

    public void setValue(ELContext context, Object value) {
    }

    public boolean isReadOnly(ELContext context) {
        return true;
    }

    public Class getType(ELContext context) {
        return null;
    }

    public Class getExpectedType() {
        return Object.class;
    }

    public String getExpressionString() {
        return iteratedExpression.getValueExpression().getExpressionString();
    }

    public boolean equals(Object obj) {
        return iteratedExpression.getValueExpression().equals(obj);
    }

    public int hashCode() {
        return iteratedExpression.getValueExpression().hashCode();
    }

    public boolean isLiteralText() {
        return false;
    }
}

