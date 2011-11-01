package com.server.entity.transfer;

import java.util.List;

import com.server.entity.ServiceTransferEntity;

/**
 * Service entity for indicator information delivery
 * 
 * @author Vladi
 *
 */
public class IndicatorServiceEntity extends ServiceTransferEntity {
	
	private List<TargetIndicator> indicatorList;
	
	public IndicatorServiceEntity(List<TargetIndicator> indicatorList)
	{
		this.indicatorList = indicatorList;
	}
	
	public List<TargetIndicator> getIndicatorList() 
	{
		return indicatorList;
	}

	/**
	 * A target indicator for the transfer entity
	 * 
	 * @author Vladi
	 *
	 */
	public static class TargetIndicator
	{
		private float positionX;
		private float positionY;
		private float positionZ;
		private String description;
		
		public TargetIndicator(float positionX, float positionY, float positionZ, String description)
		{
			this.positionX = positionX;
			this.positionY = positionY;
			this.positionZ = positionZ;
			this.description = description;
		}

		public float getPositionX()
		{
			return positionX;
		}

		public float getPositionY() 
		{
			return positionY;
		}

		public float getPositionZ()
		{
			return positionZ;
		}

		public String getDescription() 
		{
			return description;
		}
		
	}
	
}
