package com.martin.enjoypadelapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDTO {

    private String name;
    private int capacity;
    private boolean changingRooms;
    private float subscriptionPrice;
    private long city;
}
