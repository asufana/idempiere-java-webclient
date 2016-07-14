package com.github.asufana.idempiere.webclient.collections;

import java.util.*;

import org.idempiere.webservice.client.base.*;

import lombok.*;

@AllArgsConstructor
public class FieldCollection {
    
    private final List<Field> fields;
    
    public Optional<String> getValue(final String fieldName) {
        final Optional<String> value = fields.stream()
                                             .filter(field -> field.getColumn().equals(fieldName))
                                             .map(field -> field.getValue().toString())
                                             .findAny();
        return value;
    }
}
