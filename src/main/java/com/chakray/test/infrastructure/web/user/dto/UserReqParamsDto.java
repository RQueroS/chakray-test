package com.chakray.test.infrastructure.web.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserReqParamsDto {
    private static final String ALLOWED_FIELDS = "id|email|name|phone|taxId|createdAt";
    private static final String ALLOWED_OPERATORS = "eq|co|sw|ew";

    @Schema(description = "Field to sort by", example = "name")
    @Pattern(regexp = ALLOWED_FIELDS, message = "sortedBy must be one of " + ALLOWED_FIELDS)
    private String sortedBy = "id";

    @Schema(description = "Order of sorting", example = "ASC")
    @Pattern(regexp = "ASC|DESC", message = "orderBy must be one of 'ASC' or 'DESC'")
    private String orderBy = "ASC";

    @Schema(description = "Filter in the format 'field+operator+value'", example = "name+co+Alejandro")
    @Pattern(regexp = "(" + ALLOWED_FIELDS
            + ")\\+(" + ALLOWED_OPERATORS
            + ")\\+.+", message = "filter must be in the format 'field+operator+value', where field is one of "
                    + ALLOWED_FIELDS + " and operator is one of " + ALLOWED_OPERATORS)
    private String filter;
}
