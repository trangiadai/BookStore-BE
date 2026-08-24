package com.tgd.enums;

public enum SortField {
    NAME("p.name"),
    PRICE("p.selling_price"),
    CREATED_AT("p.created_at");

    private final String columnName;

    SortField(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
