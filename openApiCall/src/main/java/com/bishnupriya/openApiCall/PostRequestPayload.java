package com.bishnupriya.openApiCall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostRequestPayload {
    private String name;
    private String job;
}
