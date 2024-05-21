package com.tsf.bankingsystem.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class StatusResponse {

    @NotNull
    private Boolean success=true;

    @NotNull
    private Integer statusCode;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String moreInfo = "We will soon be providing the documentation link";

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;


    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "serviceMessage")
    private JsonNode jsonMessage;
}
