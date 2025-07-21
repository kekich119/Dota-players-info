package com.kekich.dotatest.model;

import lombok.Data;

@Data
public class Player {
    private Profile profile;
    private Integer rank_tier;
    private Integer account_id;
    private Integer win;
}