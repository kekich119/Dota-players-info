package com.kekich.dotatest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    private Integer id;
    private String name;
    private String localized_name;
    private String facet;

}
