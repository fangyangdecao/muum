package com.yhy.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ExportClassDetail(fileName = "TestClass",isExport = true)
public class TestClass {
    @Details(value = "出门证单号",isExport = true)
    //@Style(displayName = "出门证单号", width = "25")
    //@Column(value = "DOC_NUMBER")
    private String docNumber;

    @Details(value = "DOCUMENT_ID",isExport = true)
    //@Column(value = "DOCUMENT_ID")
    private Long documentId;

    @Details(value = "资产所属单位/发货单位",isExport = true)
    //@Style(displayName = "资产所属单位/发货单位", width = "25")
    //@Column(value = "ASSETS_AFF_UNIT")
    private String assetsAffUnit;
}
