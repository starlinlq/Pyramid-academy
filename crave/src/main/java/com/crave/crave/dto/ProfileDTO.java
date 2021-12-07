package com.crave.crave.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class ProfileDTO {
    private long id;
    private String name;
    private String photoUrl;
    private String status;
    private long userId;
}


