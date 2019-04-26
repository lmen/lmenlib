package slib.rmt.tools.injector;

import java.io.IOException;
import java.io.StringReader;
import java.util.function.Consumer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVIterator;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Transformer {

    private static final Consumer<JsonObjectBuilder> NO_OP = b -> {
    };

    public static void transformCsvToJson( String text, Consumer<JsonObject> evt ) throws IOException {
        transformCsvToJson( text, evt, NO_OP );
    }

    public static void transformCsvToJson( String text, Consumer<JsonObject> evt, Consumer<JsonObjectBuilder> appendFn ) throws IOException {
        final CSVParser parser = new CSVParserBuilder().withSeparator( ';' ).build();

        // skip the header row
        final CSVReader csvReader = new CSVReaderBuilder( new StringReader( text ) ).withCSVParser( parser ).build();
        CSVIterator iterator = new CSVIterator( csvReader );

        String[] header = null;
        while ( iterator.hasNext() ) {
            String[] columns = iterator.next();
            if ( header == null ) {
                header = columns;
            }
            else {
                JsonObjectBuilder jsonB = Json.createObjectBuilder();

                for ( int i = 0 ; i < columns.length ; i++ ) {
                    String v = StringUtils.trimToNull( columns[ i ] );
                    if ( v != null ) {
                        jsonB.add( header[ i ], columns[ i ] );
                    }
                }
                appendFn.accept( jsonB );

                JsonObject o = jsonB.build();
                System.out.println( o.toString() );
                evt.accept( o );
            }
        }

    }

    public static void main( String[] args ) throws IOException {
        String t = "\"RU_id\";\"RU_credit_line\";\"RU_name\";\"RU_client_entity_id\";\"RU_ref_currency_id\";\"RU_last_exposition_id\";\"RU_credit_line_currency_ref_id\";\"RU_rt_haircut\";\"RU_unique_id\";\"SC_id\";\"SC_name\";\"SC_ref_currency_id\";\"SC_unique_id\";\"CE_id\";\"CE_name\";\"CE_ref_currency_id\";\"CE_unique_id\";\"EXP_vac_id\";\"EXP_vac_beginning_date\";\"EXP_exposure\";\"PTF_type\";\"PTF_value\";\"PTF_computed_var\";\"PTF_adjusted var\";\"PTF_exposure\";\"EXP_check_price_alert\";\"EXP_out_of_date_price\";\"EXP_missing_price\";\"EXP_out_of_date_fx_rate\";\"EXP_missing_fx_rate\";\"EXP_available_capacity\";\"RU_risk_limit\";\"RU_risk_limit_currency\";\"EXP_risk_limit_rate\";\"EXP_defaulted_prices\"\n"
            + "769251;4566646;RUONLYCASH;753251;EUR;19373251;EUR;0.000000000;RU20190121120506398;531502;SC_TEST2;EUR;SC201810251234679002;753251;CE_CASH;AAJ;CE20190121115638489;19373348;2019-03-01T17:25:00.000;;CMMA;859.000000000;;;;0;0;0;0;0;;4566646;EUR;;0\n"
            + "769254;4566644;RUONLYCASH;753251;EUR;19373251;EUR;0.000000000;RU20190121120506398;531502;SC_TEST2;EUR;SC201810251234679002;753251;CE_CASH;AAJ;CE20190121115638489;19373348;2019-03-01T17:25:00.000;;CMMA;859.000000000;;;;0;0;0;0;0;;4566646;EUR;;0";

        Transformer.transformCsvToJson( t, ( json -> System.out.println( json.toString() ) ), NO_OP );
    }

}
