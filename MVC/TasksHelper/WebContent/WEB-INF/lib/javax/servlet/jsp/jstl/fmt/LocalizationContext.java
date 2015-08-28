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

package javax.servlet.jsp.jstl.fmt;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class representing an I18N localization context.
 *
 * <p> An I18N localization context has two components: a resource bundle and
 * the locale that led to the resource bundle match.
 *
 * <p> The resource bundle component is used by &lt;fmt:message&gt; for mapping
 * message keys to localized messages, and the locale component is used by the
 * &lt;fmt:message&gt;, &lt;fmt:formatNumber&gt;, &lt;fmt:parseNumber&gt;, &lt;fmt:formatDate&gt;,
 * and &lt;fmt:parseDate&gt; actions as their formatting or parsing locale, respectively.
 *
 * @author Jan Luehe
 */

public class LocalizationContext {

    // the localization context's resource bundle
    final private ResourceBundle bundle;

    // the localization context's locale
    final private Locale locale;

    /**
     * Constructs an empty I18N localization context.
     */
    public LocalizationContext() {
	bundle = null;
	locale = null;
    }

    /**
     * Constructs an I18N localization context from the given resource bundle
     * and locale.
     *
     * <p> The specified locale is the application- or browser-based preferred
     * locale that led to the resource bundle match.
     *
     * @param bundle The localization context's resource bundle
     * @param locale The localization context's locale
     */
    public LocalizationContext(ResourceBundle bundle, Locale locale) {
	this.bundle = bundle;
	this.locale = locale;
    }

    /**
     * Constructs an I18N localization context from the given resource bundle.
     *
     * <p> The localization context's locale is taken from the given
     * resource bundle.
     *
     * @param bundle The resource bundle
     */
    public LocalizationContext(ResourceBundle bundle) {
	this.bundle = bundle;
	this.locale = bundle.getLocale();
    }

    /** 
     * Gets the resource bundle of this I18N localization context.
     * 
     * @return The resource bundle of this I18N localization context, or null
     * if this I18N localization context is empty
     */ 
    public ResourceBundle getResourceBundle() {
	return bundle;
    }

    /** 
     * Gets the locale of this I18N localization context.
     *
     * @return The locale of this I18N localization context, or null if this
     * I18N localization context is empty, or its resource bundle is a
     * (locale-less) root resource bundle.
     */ 
    public Locale getLocale() {
	return locale;
    }
}

