package com.ol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Semernitskaya on 7/16/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Base unicorn entity")
public class Unicorn {

    @ApiModelProperty(dataType = "Long", notes = "Unique identifier of unicorn")
    private Long id;

    private String name;

    @ApiModelProperty(dataType = "String")
    private String description;

    public Unicorn(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
