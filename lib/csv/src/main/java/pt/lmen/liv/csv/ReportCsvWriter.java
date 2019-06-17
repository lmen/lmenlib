package pt.lmen.liv.csv;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import pt.lmen.lib.core.M;

public class ReportCsvWriter implements Closeable {

    private CSVWriter openCsvWriter;

    private List<String> headerRow;
    private List<String> row;
    private String[] opCsvArray;
    private int rowsCount = 0;

    public ReportCsvWriter( Writer ioWriter ) {
        openCsvWriter = new CSVWriter( ioWriter, ';' );
        row = new ArrayList<>();
        headerRow = new ArrayList<>();
    }

    public ReportCsvWriter header( String header ) {
        headerRow.add( header );
        return this;
    }

    public ReportCsvWriter col( String header, String value ) {
        headerRow.add( header );
        row.add( M.strOrEmpty(value ) );
        return this;
    }

    public ReportCsvWriter col( String value ) {
        row.add( M.strOrEmpty( value ) );
        return this;
    }

    public ReportCsvWriter writeHeader() {

        if ( headerRow.size() > 0 ) {
            String[] strings = new String[ headerRow.size() ];
            openCsvWriter.writeNext( headerRow.toArray( strings ) );
            headerRow.clear();
        }
        return this;
    }

    public ReportCsvWriter nextLine() {

        if ( rowsCount == 0 && headerRow.size() > 0 ) {
            String[] strings = new String[ headerRow.size() ];
            openCsvWriter.writeNext( headerRow.toArray( strings ) );
            headerRow.clear();
        }

        opCsvArray = opCsvArray == null || opCsvArray.length != row.size() ? new String[ row.size() ] : opCsvArray;
        openCsvWriter.writeNext( row.toArray( opCsvArray ), false );
        row.clear();
        rowsCount++;
        return this;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void close() throws IOException {
        openCsvWriter.close();
    }
}
