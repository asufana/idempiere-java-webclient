package com.github.asufana.idempiere.webclient.types;

import java.util.function.*;

import org.idempiere.webservice.client.base.*;
import org.idempiere.webservice.client.request.*;

import lombok.*;

@AllArgsConstructor
public enum RequestType {
                         Create(CreateDataRequest::new),
                         Query(QueryDataRequest::new),
                         Read(ReadDataRequest::new),
                         Update(UpdateDataRequest::new),
                         Delete(DeleteDataRequest::new);
                         
    /** リクエストオブジェクト */
    protected final Supplier<ModelCRUDRequest> requestObject;
    
    public ModelCRUDRequest requestObject() {
        return requestObject.get();
    }
    
}
