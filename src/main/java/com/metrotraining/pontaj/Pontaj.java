package com.metrotraining.pontaj;


import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@Table(name="pontaj")
public class Pontaj {
	
	private Long id;
	private Long startDate;
	private Long endDate;
	
	@Transient
	private int  hourDiff;
	
	@Transient
	private int  minDiff;
	
	public Pontaj() {
			super();
	}

	

	public Pontaj(Long startDate, Long endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
	
	
	
	
		 public Long getStartDate() {
		return startDate;
	}



	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}



	public Long getEndDate() {
		return endDate;
	}



	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}


	@Transient
		public int getHourDiff() {
		return hourDiff;
	}


	@Transient
	public void setHourDiff(int hourDiff) {
		this.hourDiff = hourDiff;
	}


	@Transient
	public int getMinDiff() {
		return minDiff;
	}


	@Transient
	public void setMinDiff(int minDiff) {
		this.minDiff = minDiff;
	}



		@Override
		    public String toString() {
			 String dateFormat = "dd-MM-yyyy HH:mm";
			    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			 
		        return id+"   "+simpleDateFormat.format(startDate)+ "  "+simpleDateFormat.format(endDate);
		    }
	

	
}

