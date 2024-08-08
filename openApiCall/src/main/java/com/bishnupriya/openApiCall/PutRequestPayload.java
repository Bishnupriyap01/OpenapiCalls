package com.bishnupriya.openApiCall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PutRequestPayload {
    private String first_name;
    private String last_name;
    private String email;
}
