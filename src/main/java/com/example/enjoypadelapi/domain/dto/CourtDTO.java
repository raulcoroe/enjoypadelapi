package com.example.enjoypadelapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourtDTO {

    private String surface;
    private boolean indoor;
    private boolean lighting;
    private boolean glass;
    private String partner;
    private long center;
}

