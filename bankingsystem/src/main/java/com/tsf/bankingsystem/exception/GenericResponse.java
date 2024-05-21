package com.tsf.bankingsystem.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class GenericResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "status")
    private StatusResponse statusResponse;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data")
    private T data;

}