package com.github.asufana.idempiere.webclient.functions;

import java.util.*;

import org.idempiere.webservice.client.base.*;
import org.idempiere.webservice.client.base.Enums.*;
import org.idempiere.webservice.client.response.*;

import com.github.asufana.idempiere.webclient.collections.*;

import lombok.*;

@AllArgsConstructor
public class WSResponse {
    
    protected final WebServiceResponse response;
    
    public Integer size() {
        if (response instanceof WindowTabDataResponse) {
            return ((WindowTabDataResponse) response).getTotalRows();
        }
        if (response instanceof StandardResponse) {
            return 1;
        }
        throw new RuntimeException();
    }
    
    public Integer recordId() {
        if (response instanceof StandardResponse) {
            return ((StandardResponse) response).getRecordID();
        }
        throw new RuntimeException("This is not StandardResponse. Response: "
                + response.getClass());
    }
    
    public FieldCollection getFields(final Integer index) {
        if (response instanceof WindowTabDataResponse) {
            final DataSet dataSet = ((WindowTabDataResponse) response).getDataSet();
            final List<Field> fields = dataSet.getRow(index).getFields();
            return new FieldCollection(fields);
        }
        throw new RuntimeException("This is not WindowTabDataResponse. Response: "
                + response.getClass());
    }
    
    public WebServiceResponseStatus status() {
        return response.getStatus();
    }
    
    public boolean isSuccess() {
        return response.getStatus().equals(WebServiceResponseStatus.Successful);
    }
    
    @Override
    public String toString() {
        if (response instanceof WindowTabDataResponse) {
            return toString((WindowTabDataResponse) response);
        }
        if (response instanceof StandardResponse) {
            return toString((StandardResponse) response);
        }
        throw new RuntimeException();
    }
    
    protected String toString(final WindowTabDataResponse response) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Total rows: " + response.getTotalRows());
        sb.append("\n");
        sb.append("Num rows: " + response.getNumRows());
        sb.append("\n");
        sb.append("Start row: " + response.getStartRow());
        sb.append("\n");
        sb.append("\n");
        for (int i = 0; i < response.getDataSet().getRowsCount(); i++) {
            sb.append("Row: " + (i + 1));
            sb.append("\n");
            for (int j = 0; j < response.getDataSet().getRow(i).getFieldsCount(); j++) {
                final Field field = response.getDataSet().getRow(i).getFields().get(j);
                sb.append("Column: " + field.getColumn() + " = " + field.getStringValue());
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    protected String toString(final StandardResponse response) {
        final StringBuilder sb = new StringBuilder();
        sb.append("RecordID: " + response.getRecordID());
        sb.append("\n");
        sb.append("\n");
        
        for (int i = 0; i < response.getOutputFields().getFieldsCount(); i++) {
            sb.append(String.format("Column%s: %s = %s",
                                    (i + 1),
                                    response.getOutputFields().getField(i).getColumn(),
                                    response.getOutputFields().getField(i).getValue()));
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    
}
