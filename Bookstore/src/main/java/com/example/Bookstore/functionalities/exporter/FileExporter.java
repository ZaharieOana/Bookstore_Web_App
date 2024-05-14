package com.example.Bookstore.functionalities.exporter;

import com.example.Bookstore.dto.SaleExportDTO;

import java.util.List;

public interface FileExporter {
    String exportSales(List<SaleExportDTO> sales);
}
