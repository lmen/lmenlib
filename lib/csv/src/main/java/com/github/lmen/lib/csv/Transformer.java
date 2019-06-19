package com.github.lmen.lib.csv;

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
        String t = "\"club\";\"player\";\n"
            + "benfica;samaris;\n"
            + "benfica;ruben dias;";

        Transformer.transformCsvToJson( t, ( json -> System.out.println( json.toString() ) ), NO_OP );
    }

}
