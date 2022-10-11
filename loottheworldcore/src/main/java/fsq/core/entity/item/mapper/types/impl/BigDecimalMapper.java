package fsq.core.entity.item.mapper.types.impl;

import fsq.core.entity.item.mapper.types.TypeMapper;

import java.math.BigDecimal;

public class BigDecimalMapper implements TypeMapper<BigDecimal> {
    @Override
    public BigDecimal mapValue(Object value) {
        if (value instanceof String valStr) {
            return new BigDecimal(valStr);
        }
        return BigDecimal.ZERO;
    }
}
