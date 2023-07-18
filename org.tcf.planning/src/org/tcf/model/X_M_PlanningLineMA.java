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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_PlanningLineMA
 *  @author iDempiere (generated) 
 *  @version Release 9 - $Id$ */
@org.adempiere.base.Model(table="M_PlanningLineMA")
public class X_M_PlanningLineMA extends PO implements I_M_PlanningLineMA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230717L;

    /** Standard Constructor */
    public X_M_PlanningLineMA (Properties ctx, int M_PlanningLineMA_ID, String trxName)
    {
      super (ctx, M_PlanningLineMA_ID, trxName);
      /** if (M_PlanningLineMA_ID == 0)
        {
			setM_AttributeSetInstance_ID (0);
			setMovementQty (Env.ZERO);
			setM_PlanningLine_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_M_PlanningLineMA (Properties ctx, int M_PlanningLineMA_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, M_PlanningLineMA_ID, trxName, virtualColumns);
      /** if (M_PlanningLineMA_ID == 0)
        {
			setM_AttributeSetInstance_ID (0);
			setMovementQty (Env.ZERO);
			setM_PlanningLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_PlanningLineMA (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_M_PlanningLineMA[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date  Material Policy.
		@param DateMaterialPolicy Time used for LIFO and FIFO Material Policy
	*/
	public void setDateMaterialPolicy (Timestamp DateMaterialPolicy)
	{
		set_ValueNoCheck (COLUMNNAME_DateMaterialPolicy, DateMaterialPolicy);
	}

	/** Get Date  Material Policy.
		@return Time used for LIFO and FIFO Material Policy
	  */
	public Timestamp getDateMaterialPolicy()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateMaterialPolicy);
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
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
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

	public I_M_PlanningLine getM_PlanningLine() throws RuntimeException
	{
		return (I_M_PlanningLine)MTable.get(getCtx(), I_M_PlanningLine.Table_ID)
			.getPO(getM_PlanningLine_ID(), get_TrxName());
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_PlanningLine_ID()));
    }

	/** Set M_PlanningLineMA_UU.
		@param M_PlanningLineMA_UU M_PlanningLineMA_UU
	*/
	public void setM_PlanningLineMA_UU (String M_PlanningLineMA_UU)
	{
		set_Value (COLUMNNAME_M_PlanningLineMA_UU, M_PlanningLineMA_UU);
	}

	/** Get M_PlanningLineMA_UU.
		@return M_PlanningLineMA_UU	  */
	public String getM_PlanningLineMA_UU()
	{
		return (String)get_Value(COLUMNNAME_M_PlanningLineMA_UU);
	}
}