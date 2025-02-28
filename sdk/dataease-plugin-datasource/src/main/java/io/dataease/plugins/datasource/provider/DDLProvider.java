package io.dataease.plugins.datasource.provider;

import io.dataease.plugins.common.base.domain.DatasetTableField;
import io.dataease.plugins.common.base.domain.Datasource;
import io.dataease.plugins.common.dto.dataset.DataTableInfoDTO;

import java.util.List;

/**
 * @Author gin
 * @Date 2021/5/17 4:19 下午
 */
public abstract class DDLProvider {
    public abstract String createView(String name, String viewSQL);

    public abstract String dropTable(String name);

    public abstract String dropView(String name);

    public abstract String replaceTable(String name);

    public abstract String createTableSql(DataTableInfoDTO dataTableInfoDTO, String name, List<DatasetTableField> datasetTableFields, Datasource engine, String version);

    public abstract String insertSql(String name, List<String[]> dataList, int page, int pageNumber);
}
