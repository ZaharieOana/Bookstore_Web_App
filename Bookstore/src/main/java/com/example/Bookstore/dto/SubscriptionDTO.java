package com.example.Bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SubscriptionDTO {
    private String email;
    private boolean ok;
}
