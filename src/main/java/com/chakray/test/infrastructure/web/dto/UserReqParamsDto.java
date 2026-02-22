package com.chakray.test.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserReqParamsDto {
    @Schema(description = "Field to sort by", example = "name")
    @Pattern(regexp = "id|email|name|phone|taxId|createdAt", message = "sortedBy must be one of 'id', 'email', 'name', 'phone', 'taxId', or 'createdAt'")
    private String sortedBy = "id";

    @Schema(description = "Order of sorting", example = "ASC")
    @Pattern(regexp = "ASC|DESC", message = "orderBy must be one of 'ASC' or 'DESC'")
    private String orderBy = "ASC";
}
