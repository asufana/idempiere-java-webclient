package com.github.asufana.idempiere.webclient;

import org.idempiere.webservice.client.base.DataRow;

import com.github.asufana.idempiere.webclient.factories.DataRowBuilder;
import com.github.asufana.idempiere.webclient.factories.RequestFactory;
import com.github.asufana.idempiere.webclient.functions.WSRequest;
import com.github.asufana.idempiere.webclient.functions.WSResponse;
import com.github.asufana.idempiere.webclient.types.RequestTable;

import lombok.NonNull;

public class BPartnerRepo {
    
    /** Create */
    public static WSResponse create(@NonNull final String name, @NonNull final String value) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name)
                                                    .add("Value",
                                                         value + "_" + System.currentTimeMillis())
                                                    .add("TaxID", "-")
                                                    .build();
        final WSRequest request = RequestFactory.create(RequestTable.BPartner, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Query */
    public static WSResponse findByName(@NonNull final String name) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name).build();
        final WSRequest request = RequestFactory.query(RequestTable.BPartner, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /* Read */
    public static WSResponse findById(@NonNull final Integer recordId) {
        final WSRequest request = RequestFactory.read(RequestTable.BPartner, recordId);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Update */
    public static WSResponse update(@NonNull final Integer recordId, @NonNull final String name) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name).build();
        final WSRequest request = RequestFactory.update(RequestTable.BPartner, recordId, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Delete */
    public static WSResponse delete(@NonNull final Integer recordId) {
        final WSRequest request = RequestFactory.delete(RequestTable.BPartner, recordId);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
}
