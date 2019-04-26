package com.slib.var.webapp.pages.holidays;

import java.util.List;

import com.slib.var.core.holidays.HolidaysStore.HolidaysSet;
import com.slib.var.webapp.mvc.Model;

public class HolidaysModel implements Model {
    
    public static class MarketHolidays {
        
        String mic;
        HolidaysSet holidaysSet;        
        
    }
    
    String marketDefaultHolidaySet;
    
    List<HolidaysSet> holidaysSetList;
    
    List<MarketHolidays> marketHolidaysList;
    
    
}
