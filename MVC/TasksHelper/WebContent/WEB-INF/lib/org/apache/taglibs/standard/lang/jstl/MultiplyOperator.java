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

package org.apache.taglibs.standard.lang.jstl;

/**
 *
 * <p>The implementation of the multiply operator
 * 
 * @author Nathan Abramson - Art Technology Group
 * @version $Change: 181177 $$DateTime: 2001/06/26 08:45:09 $$Author: kchung $
 **/

public class MultiplyOperator
  extends ArithmeticOperator
{
  //-------------------------------------
  // Singleton
  //-------------------------------------

  public static final MultiplyOperator SINGLETON =
    new MultiplyOperator ();

  //-------------------------------------
  /**
   *
   * Constructor
   **/
  public MultiplyOperator ()
  {
  }

  //-------------------------------------
  // Expression methods
  //-------------------------------------
  /**
   *
   * Returns the symbol representing the operator
   **/
  public String getOperatorSymbol ()
  {
    return "*";
  }

  //-------------------------------------
  /**
   *
   * Applies the operator to the given double values, returning a double
   **/
  public double apply (double pLeft,
		       double pRight,
		       Logger pLogger)
  {
    return pLeft * pRight;
  }
  
  //-------------------------------------
  /**
   *
   * Applies the operator to the given double values, returning a double
   **/
  public long apply (long pLeft,
		     long pRight,
		     Logger pLogger)
  {
    return pLeft * pRight;
  }
  
  //-------------------------------------
}
