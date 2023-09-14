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

/** Generated Model for M_PlanningLine
 *  @author iDempiere (generated) 
 *  @version Release 9 - $Id$ */
@org.adempiere.base.Model(table="M_PlanningLine")
public class X_M_PlanningLine extends PO implements I_M_PlanningLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230804L;

    /** Standard Constructor */
    public X_M_PlanningLine (Properties ctx, int M_PlanningLine_ID, String trxName)
    {
      super (ctx, M_PlanningLine_ID, trxName);
      /** if (M_PlanningLine_ID == 0)
        {
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_PlanningLine WHERE M_Planning_ID=@M_Planning_ID@
			setM_Locator_ID (0);
// @M_Locator_ID@
			setMovementQty (Env.ZERO);
			setM_PlanningLine_ID (0);
			setM_Product_ID (0);
			setProcessed (false);
        } */
    }

    /** Standard Constructor */
    public X_M_PlanningLine (Properties ctx, int M_PlanningLine_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, M_PlanningLine_ID, trxName, virtualColumns);
      /** if (M_PlanningLine_ID == 0)
        {
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_PlanningLine WHERE M_Planning_ID=@M_Planning_ID@
			setM_Locator_ID (0);
// @M_Locator_ID@
			setMovementQty (Env.ZERO);
			setM_PlanningLine_ID (0);
			setM_Product_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_M_PlanningLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuilder sb = new StringBuilder ("X_M_PlanningLine[")
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

	/** Set End Product.
		@param IsEndProduct End Product of production
	*/
	public void setIsEndProduct (boolean IsEndProduct)
	{
		set_Value (COLUMNNAME_IsEndProduct, Boolean.valueOf(IsEndProduct));
	}

	/** Get End Product.
		@return End Product of production
	  */
	public boolean isEndProduct()
	{
		Object oo = get_Value(COLUMNNAME_IsEndProduct);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
	{
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_ID)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID Product Attribute Set Instance
	*/
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0)
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Movement Quantity.
		@param MovementQty Quantity of a product moved.
	*/
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_Planning_ID()));
    }

	/** Set Planning Line.
		@param M_PlanningLine_ID Planning Line
	*/
	public void setM_PlanningLine_ID (int M_PlanningLine_ID)
	{
		if (M_PlanningLine_ID < 1)
			set_ValueNoCheck (COLUMNNAME_M_PlanningLine_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_PlanningLine_ID, Integer.valueOf(M_PlanningLine_ID));
	}

	/** Get Planning Line.
		@return Planning Line	  */
	public int getM_PlanningLine_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PlanningLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_PlanningLine_UU.
		@param M_PlanningLine_UU M_PlanningLine_UU
	*/
	public void setM_PlanningLine_UU (String M_PlanningLine_UU)
	{
		set_Value (COLUMNNAME_M_PlanningLine_UU, M_PlanningLine_UU);
	}

	/** Get M_PlanningLine_UU.
		@return M_PlanningLine_UU	  */
	public String getM_PlanningLine_UU()
	{
		return (String)get_Value(COLUMNNAME_M_PlanningLine_UU);
	}

	public I_M_PlanningProduct getM_PlanningProduct() throws RuntimeException
	{
		return (I_M_PlanningProduct)MTable.get(getCtx(), I_M_PlanningProduct.Table_ID)
			.getPO(getM_PlanningProduct_ID(), get_TrxName());
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

	/** Set Planned Quantity.
		@param PlannedQty Planned quantity for this project
	*/
	public void setPlannedQty (BigDecimal PlannedQty)
	{
		set_Value (COLUMNNAME_PlannedQty, PlannedQty);
	}

	/** Get Planned Quantity.
		@return Planned quantity for this project
	  */
	public BigDecimal getPlannedQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PlannedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Quantity Used.
		@param QtyUsed Quantity Used
	*/
	public void setQtyUsed (BigDecimal QtyUsed)
	{
		set_Value (COLUMNNAME_QtyUsed, QtyUsed);
	}

	/** Get Quantity Used.
		@return Quantity Used	  */
	public BigDecimal getQtyUsed()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}