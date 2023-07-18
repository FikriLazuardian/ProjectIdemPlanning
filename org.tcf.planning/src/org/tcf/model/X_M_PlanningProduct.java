/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.tcf.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_PlanningProduct
 *  @author iDempiere (generated) 
 *  @version Release 9 - $Id$ */
@org.adempiere.base.Model(table="M_PlanningProduct")
public class X_M_PlanningProduct extends PO implements I_M_PlanningProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230717L;

    /** Standard Constructor */
    public X_M_PlanningProduct (Properties ctx, int M_PlanningProduct_ID, String trxName)
    {
      super (ctx, M_PlanningProduct_ID, trxName);
      /** if (M_PlanningProduct_ID == 0)
        {
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_PlanningProduct WHERE M_Planning_ID=@M_Planning_ID@
			setM_Locator_ID (0);
// @M_Locator_ID@
			setM_Planning_ID (0);
			setM_PlanningProduct_ID (0);
			setM_Product_ID (0);
			setProcessed (false);
			setProductionPlanQty (Env.ZERO);
// 1
        } */
    }

    /** Standard Constructor */
    public X_M_PlanningProduct (Properties ctx, int M_PlanningProduct_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, M_PlanningProduct_ID, trxName, virtualColumns);
      /** if (M_PlanningProduct_ID == 0)
        {
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_PlanningProduct WHERE M_Planning_ID=@M_Planning_ID@
			setM_Locator_ID (0);
// @M_Locator_ID@
			setM_Planning_ID (0);
			setM_PlanningProduct_ID (0);
			setM_Product_ID (0);
			setProcessed (false);
			setProductionPlanQty (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_M_PlanningProduct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_M_PlanningProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Line No.
		@param Line Unique line for this document
	*/
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
    }

	public I_M_Locator getM_Locator() throws RuntimeException
	{
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_ID)
			.getPO(getM_Locator_ID(), get_TrxName());
	}

	/** Set Locator.
		@param M_Locator_ID Warehouse Locator
	*/
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Planning getM_Planning() throws RuntimeException
	{
		return (I_M_Planning)MTable.get(getCtx(), I_M_Planning.Table_ID)
			.getPO(getM_Planning_ID(), get_TrxName());
	}

	/** Set Planning.
		@param M_Planning_ID Planning
	*/
	public void setM_Planning_ID (int M_Planning_ID)
	{
		if (M_Planning_ID < 1)
			set_ValueNoCheck (COLUMNNAME_M_Planning_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_Planning_ID, Integer.valueOf(M_Planning_ID));
	}

	/** Get Planning.
		@return Planning	  */
	public int getM_Planning_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Planning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Planning Product.
		@param M_PlanningProduct_ID Planning Product
	*/
	public void setM_PlanningProduct_ID (int M_PlanningProduct_ID)
	{
		if (M_PlanningProduct_ID < 1)
			set_ValueNoCheck (COLUMNNAME_M_PlanningProduct_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_PlanningProduct_ID, Integer.valueOf(M_PlanningProduct_ID));
	}

	/** Get Planning Product.
		@return Planning Product	  */
	public int getM_PlanningProduct_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PlanningProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_PlanningProduct_UU.
		@param M_PlanningProduct_UU M_PlanningProduct_UU
	*/
	public void setM_PlanningProduct_UU (String M_PlanningProduct_UU)
	{
		set_Value (COLUMNNAME_M_PlanningProduct_UU, M_PlanningProduct_UU);
	}

	/** Get M_PlanningProduct_UU.
		@return M_PlanningProduct_UU	  */
	public String getM_PlanningProduct_UU()
	{
		return (String)get_Value(COLUMNNAME_M_PlanningProduct_UU);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_ID)
			.getPO(getM_Product_ID(), get_TrxName());
	}

	/** Set Product.
		@param M_Product_ID Product, Service, Item
	*/
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			set_Value (COLUMNNAME_M_Product_ID, null);
		else
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ProductionPlanQty.
		@param ProductionPlanQty ProductionPlanQty
	*/
	public void setProductionPlanQty (BigDecimal ProductionPlanQty)
	{
		set_Value (COLUMNNAME_ProductionPlanQty, ProductionPlanQty);
	}

	/** Get ProductionPlanQty.
		@return ProductionPlanQty
	  */
	public BigDecimal getProductionPlanQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProductionPlanQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}