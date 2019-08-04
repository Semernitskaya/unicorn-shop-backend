package com.ol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Semernitskaya on 7/16/19.
 */
@Data
@AllArgsConstructor
@ApiModel(description = "Base unicorn entity")
public class Unicorn {

    @ApiModelProperty(dataType = "Long", notes = "Unique identifier of unicorn")
    private Long id;

    @ApiModelProperty(dataType = "String")
    private String description;
}
