package com.example.Bookstore.dto;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportDTO {
    @XmlElement
    private double sum;
    @XmlElement
    private LocalDate date;
    @XmlElement
    private String user;
    @XmlElement
    private String books;
}
