package net.tanguydev.customerservice.Infrastructure.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
}
