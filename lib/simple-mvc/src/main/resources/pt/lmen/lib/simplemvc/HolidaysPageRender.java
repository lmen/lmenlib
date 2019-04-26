package com.slib.var.webapp.pages.holidays;

import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.slib.var.core.holidays.HolidaysStore.HolidaysSet;
import com.slib.var.webapp.mvc.AbstractPageRenderer;
import com.slib.var.webapp.mvc.PageState;
import com.slib.var.webapp.mvc.PageTemplate;
import com.slib.var.webapp.mvc.Partials;
import com.slib.var.webapp.mvc.Sequence;
import com.slib.var.webapp.pages.holidays.HolidaysModel.MarketHolidays;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

class HolidaysPageRender extends AbstractPageRenderer<HolidaysModel> {

    public HolidaysPageRender( PageState state, HolidaysModel model ) {
        super( state, model );
    }

    public String render() {

        Sequence seq =Partials.seq(
            
            h1("Holidays Information "),            
            Partials.constantField( "Default Holiday Set", getModel().marketDefaultHolidaySet ),
            
            h2( "Holiday Sets"),                                
            renderHolidaySetList(),
            
            h2( "Holidays by Markets"),
            renderMarketsList()
            
        );
                        
        return PageTemplate.page( seq );
    }

    
    private DomContent renderHolidaySetList(  ) {
        
        List<HolidaysSet> data = getModel().holidaysSetList;
        if ( data == null || data.isEmpty() ) {
            return Partials.Table.noRows( "No holidaysSets" );
        }        
        
        ContainerTag table = Partials.Table.start( "Holiday Set Name", "Holidays days");
                
        for ( HolidaysSet hSet : data ) {
            String name = hSet.getName();
            
            String daysList = null; 
            List<LocalDate> days = hSet.getDays();
            if (days != null) {
                daysList = StringUtils.join( days, ", " );
            } else {
                daysList  =  "none";
            }
                        
            table.with( Partials.Table.row( name, daysList ));
        }

        return table;

    }
    
    private DomContent renderMarketsList(  ) {
        
        List<MarketHolidays> data = getModel().marketHolidaysList;
        if ( data == null || data.isEmpty() ) {
            return Partials.Table.noRows( "Markets list" );
        }        
        
        ContainerTag table = Partials.Table.start( "Mic", "Holiday Set Name", "Holidays days");
                
        for ( MarketHolidays mktH : data ) {
            String mic = mktH.mic;
            
            String name = null;
            String daysList = null;
            
            HolidaysSet hSet = mktH.holidaysSet;
            if (hSet != null) {
                name = hSet.getName();
            
                
                List<LocalDate> days = hSet.getDays();
                if (days != null) {                    
                   daysList = StringUtils.join( days, ", " );
                } else {
                    daysList = "none";
                }
            }
                        
            table.with( Partials.Table.row( mic, name, daysList ));
        }

        return table;

    }

    
}
