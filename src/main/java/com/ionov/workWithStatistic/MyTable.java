package com.ionov.workWithStatistic;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTable extends AbstractTableModel {
    private List<String> columnName;
    private List<Statistic> data;

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public void setData(List<Statistic> data) {
        this.data = data;
    }

    public List<Statistic> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Statistic sta = data.get(rowIndex);
        String column = columnName.get(columnIndex);
        return sta.getData(column);
    }

    @Override
    public String getColumnName(int column) {
        return columnName.get(column);
    }
}
