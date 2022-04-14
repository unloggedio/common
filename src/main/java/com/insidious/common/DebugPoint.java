package com.insidious.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DebugPoint {
    @NotBlank
    private String file;

    @NotBlank
    private Integer lineNumber;

}
