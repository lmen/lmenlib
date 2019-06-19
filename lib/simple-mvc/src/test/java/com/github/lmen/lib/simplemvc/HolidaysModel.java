package com.github.lmen.lib.simplemvc;

import java.time.LocalDate;
import java.util.List;

import com.github.lmen.lib.simplemvc.Model;


public class HolidaysModel implements Model {
    
	public static class HolidaysSet {
		private String name;
		private List<LocalDate> days;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<LocalDate> getDays() {
			return days;
		}

		public void setDays(List<LocalDate> days) {
			this.days = days;
		}
		
		
		
	}
	
    public static class MarketHolidays {
        
        String mic;
        HolidaysSet holidaysSet;        
        
    }
    
    String marketDefaultHolidaySet;
    
    List<HolidaysSet> holidaysSetList;
    
    List<MarketHolidays> marketHolidaysList;
    
    
}
