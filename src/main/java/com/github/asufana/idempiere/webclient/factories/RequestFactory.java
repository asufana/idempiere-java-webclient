package com.github.asufana.idempiere.webclient.factories;

import static org.apache.commons.lang3.StringUtils.*;

import org.idempiere.webservice.client.base.*;

import com.github.asufana.idempiere.webclient.functions.*;
import com.github.asufana.idempiere.webclient.types.*;

import lombok.*;

@AllArgsConstructor
public class RequestFactory {
    
    /** Create */
    public static WSRequest create(@NonNull final RequestTable table,
                                   @NonNull final DataRow dataRow) {
        return create(createWebServiceTypeName(RequestType.Create, table), dataRow);
    }
    
    /** Create */
    public static WSRequest create(@NonNull final String iDempiereWebServiceTypeName,
                                   @NonNull final DataRow dataRow) {
        if (isEmpty(iDempiereWebServiceTypeName)) {
            throw new RuntimeException();
        }
        
        final WSRequest request = createRequestObject(RequestType.Create,
                                                      iDempiereWebServiceTypeName);
        request.setDataRow(dataRow);
        return request;
    }
    
    /** Query */
    public static WSRequest query(@NonNull final RequestTable table,
                                  @NonNull final DataRow dataRow) {
        return query(createWebServiceTypeName(RequestType.Query, table), dataRow);
    }
    
    /** Query */
    public static WSRequest query(@NonNull final String iDempiereWebServiceTypeName,
                                  @NonNull final DataRow dataRow) {
        if (isEmpty(iDempiereWebServiceTypeName)) {
            throw new RuntimeException();
        }
        
        final WSRequest request = createRequestObject(RequestType.Query,
                                                      iDempiereWebServiceTypeName);
        request.setDataRow(dataRow);
        return request;
    }
    
    /** Read */
    public static WSRequest read(@NonNull final RequestTable table,
                                 @NonNull final Integer recordId) {
        return read(createWebServiceTypeName(RequestType.Read, table), recordId);
    }
    
    /** Read */
    public static WSRequest read(@NonNull final String iDempiereWebServiceTypeName,
                                 @NonNull final Integer recordId) {
        if (isEmpty(iDempiereWebServiceTypeName)) {
            throw new RuntimeException();
        }
        
        final WSRequest request = createRequestObject(RequestType.Read,
                                                      iDempiereWebServiceTypeName);
        request.setRecordId(recordId);
        return request;
    }
    
    /** Update */
    public static WSRequest update(@NonNull final RequestTable table,
                                   @NonNull final Integer recordId,
                                   @NonNull final DataRow dataRow) {
        return update(createWebServiceTypeName(RequestType.Update, table), recordId, dataRow);
    }
    
    /** Update */
    public static WSRequest update(@NonNull final String iDempiereWebServiceTypeName,
                                   @NonNull final Integer recordId,
                                   @NonNull final DataRow dataRow) {
        if (isEmpty(iDempiereWebServiceTypeName)) {
            throw new RuntimeException();
        }
        
        final WSRequest request = createRequestObject(RequestType.Update,
                                                      iDempiereWebServiceTypeName);
        request.setRecordId(recordId).setDataRow(dataRow);
        return request;
    }
    
    /** Delete */
    public static WSRequest delete(@NonNull final RequestTable table,
                                   @NonNull final Integer recordId) {
        return delete(createWebServiceTypeName(RequestType.Delete, table), recordId);
    }
    
    /** Delete */
    public static WSRequest delete(@NonNull final String iDempiereWebServiceTypeName,
                                   @NonNull final Integer recordId) {
        if (isEmpty(iDempiereWebServiceTypeName)) {
            throw new RuntimeException();
        }
        
        final WSRequest request = createRequestObject(RequestType.Delete,
                                                      iDempiereWebServiceTypeName);
        request.setRecordId(recordId);
        return request;
    }
    
    /** Create request object */
    protected static WSRequest createRequestObject(final RequestType type,
                                                   final String iDempiereWebServiceTypeName) {
        final ModelCRUDRequest request = type.requestObject();
        request.setWebServiceType(iDempiereWebServiceTypeName);
        return new WSRequest(request);
    }
    
    /** Create service type name */
    protected static String createWebServiceTypeName(final RequestType type,
                                                     final RequestTable table) {
        // ex. WS_QueryBPartner, WS_CreateBPartner, WS_ReadBPartner...
        return String.format("WS_%s%s", type.name(), table.name());
    }
}
