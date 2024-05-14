package com.example.Bookstore.functionalities.exporter;

import com.example.Bookstore.dto.SaleExportDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

public class XMLFileExporter implements FileExporter{
    @Override
    public String exportSales(List<SaleExportDTO> sales) {
        String xmlContent = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(SaleExportDTO.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();


            for(SaleExportDTO s: sales)
                jaxbMarshaller.marshal(s, sw);
//            if (object instanceof List) {
//                List<?> list = (List<?>) object;
//                for (Object item : list) {
//                    jaxbMarshaller.marshal(item, sw);
//                }
//            } else {
//                jaxbMarshaller.marshal(object, sw);
//            }
            xmlContent = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlContent;
    }
}
