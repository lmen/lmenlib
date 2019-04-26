package com.slib.var.webapp.pages.holidays;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slib.var.core.history.config.HistoryConfig;
import com.slib.var.core.holidays.HolidaysStore;
import com.slib.var.webapp.mvc.AbstractPageRenderer;
import com.slib.var.webapp.mvc.AbstractRequestHandler;
import com.slib.var.webapp.mvc.PageState;
import com.slib.var.webapp.pages.holidays.HolidaysModel.MarketHolidays;

class HolidaysRequestHandler extends AbstractRequestHandler<HolidaysModel> {

    public static final String ASK_SECURITIES = "AskSecurities";
    public static final String FROM_DATE = "fromDate";
    public static final String MIC = "mic";
    
    private HistoryConfig historyConfig;
    
    private HolidaysStore holidaysStore;

    private static final String[] STATE_KEYS = {};

    public HolidaysRequestHandler( HistoryConfig historyConfig, HolidaysStore holidaysStore ) {
        super( STATE_KEYS );
        this.holidaysStore = holidaysStore;
        this.historyConfig = historyConfig; 
    }

    @Override
    public HolidaysModel handleAction( String action, HttpServletRequest req, HttpServletResponse resp, PageState state ) {

        HolidaysModel model = new HolidaysModel();
        model.marketDefaultHolidaySet = historyConfig.getMarketsDefaultHolidaySet();
        model.holidaysSetList = holidaysStore.getHolidaysSets();
        
        model.marketHolidaysList = historyConfig.getMarketsConfig().stream().map( mktCfg -> {
            MarketHolidays hol = new HolidaysModel.MarketHolidays();
            hol.mic = mktCfg.getMic();            
            hol.holidaysSet = holidaysStore.findHolidaysSetForMic( hol.mic );
            return hol;
        }).collect( Collectors.toList() );
        

        return model;
    }

    @Override
    public AbstractPageRenderer<HolidaysModel> createPageRenderer( PageState state, HolidaysModel model ) {
        return new HolidaysPageRender( state, model );
    }

}
