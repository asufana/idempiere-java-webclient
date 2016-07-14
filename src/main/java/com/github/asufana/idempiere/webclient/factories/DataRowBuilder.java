package com.github.asufana.idempiere.webclient.factories;

import org.idempiere.webservice.client.base.*;

public class DataRowBuilder {
    
    private final DataRow dataRow = new DataRow();
    
    public DataRowBuilder() {}
    
    public DataRowBuilder add(final String key, final String value) {
        dataRow.addField(key, value);
        return this;
    }
    
    public DataRowBuilder add(final String key, final Object value) {
        dataRow.addField(key, value);
        return this;
    }
    
    public DataRow build() {
        return dataRow;
    }
}
