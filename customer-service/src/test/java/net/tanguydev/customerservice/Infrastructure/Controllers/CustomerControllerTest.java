package net.tanguydev.customerservice.Infrastructure.Controllers;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Presenter.CustomerPresenterInterface;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;
import net.tanguydev.customerservice.Domain.UseCases.CreateCustomerUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.GetCustomerByIdUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.ListCustomersUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.UpdateCustomerUseCaseInterface;
import net.tanguydev.customerservice.Infrastructure.Controllers.CustomerController;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Request.CustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class CustomerControllerTest {

    private MockMvc mockMvc;
    private CreateCustomerUseCaseInterface createCustomerUseCase;
    private ListCustomersUseCaseInterface listCustomersUseCase;
    private GetCustomerByIdUseCaseInterface getCustomerByIdUseCase;
    private UpdateCustomerUseCaseInterface updateCustomerUseCase;
    private CustomerPresenterInterface presenter;
    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        createCustomerUseCase = mock(CreateCustomerUseCaseInterface.class);
        listCustomersUseCase = mock(ListCustomersUseCaseInterface.class);
        getCustomerByIdUseCase = mock(GetCustomerByIdUseCaseInterface.class);
        updateCustomerUseCase = mock(UpdateCustomerUseCaseInterface.class);
        presenter = mock(CustomerPresenterInterface.class);
        mapper = mock(CustomerMapper.class);

        CustomerController controller = new CustomerController(createCustomerUseCase, listCustomersUseCase, getCustomerByIdUseCase, updateCustomerUseCase, presenter, mapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnAllCustomers() throws Exception {
        // Given
        List<DomainCustomer> domainCustomers = Arrays.asList(
                new DomainCustomer(1L, "Alice", "alice@mail.com", "0601")
        );
        List<CustomerResponse> responses = Arrays.asList(
                new CustomerResponse(1L, "Alice", "alice@mail.com","5665353553723")
        );

        when(listCustomersUseCase.execute()).thenReturn(domainCustomers);
        when(presenter.presentList(domainCustomers)).thenReturn(responses);

        // When & Then
        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fullName").value("Alice"));
    }

    @Test
    void shouldCreateCustomer() throws Exception {
        // Given
        String requestJson = "{\"fullName\": \"Bob\", \"email\": \"bob@mail.com\"}";
        DomainCustomer domainCustomer = new DomainCustomer(null, "Bob", "bob@mail.com", null);
        DomainCustomer savedCustomer = new DomainCustomer(1L, "Bob", "bob@mail.com", null);
        CustomerResponse response = new CustomerResponse(1L, "Bob", "bob@mail.com","232355273");

        when(mapper.toDomain(any(CustomerRequest.class))).thenReturn(domainCustomer);
        when(createCustomerUseCase.execute(domainCustomer)).thenReturn(savedCustomer);
        when(presenter.present(savedCustomer)).thenReturn(response);

        // When & Then
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Bob"));
    }
}
