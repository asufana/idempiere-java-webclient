package com.github.asufana.idempiere.webclient.functions;

import org.idempiere.webservice.client.base.*;
import org.idempiere.webservice.client.base.Enums.*;
import org.idempiere.webservice.client.net.*;

import lombok.*;

@AllArgsConstructor
public class WSRequest {
    
    protected final ModelCRUDRequest request;
    
    public WSRequest setRecordId(final Integer recordId) {
        request.setRecordID(recordId);
        return this;
    }
    
    public WSRequest setDataRow(final DataRow dataRow) {
        request.setDataRow(dataRow);
        return this;
    }
    
    public WSResponse sendRequest(final LoginRequest loginRequest,
                                  final WebServiceConnection webServiceConnection) {
        request.setLogin(loginRequest);
        
        final WebServiceResponse response = sendRequest(request, webServiceConnection);
        if (response.getStatus() == WebServiceResponseStatus.Error) {
            throw new RuntimeException(response.getErrorMessage());
        }
        return new WSResponse(response);
    }
    
    protected WebServiceResponse sendRequest(final WebServiceRequest request,
                                             final WebServiceConnection client) {
        try {
            return client.sendRequest(request);
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
