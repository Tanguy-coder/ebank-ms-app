package net.tanguydev.customerservice.Infrastructure.Controllers;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Presenter.CustomerPresenterInterface;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;
import net.tanguydev.customerservice.Domain.UseCases.CreateCustomerUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.GetCustomerByIdUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.ListCustomersUseCaseInterface;
import net.tanguydev.customerservice.Domain.UseCases.UpdateCustomerUseCaseInterface;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Request.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CreateCustomerUseCaseInterface createCustomerUseCase;
    private final ListCustomersUseCaseInterface listCustomersUseCase;
    private final GetCustomerByIdUseCaseInterface getCustomerByIdUseCase;
    private final UpdateCustomerUseCaseInterface updateCustomerUseCase;
    private final CustomerPresenterInterface presenter;
    private final CustomerMapper mapper;

    public CustomerController(
            CreateCustomerUseCaseInterface createCustomerUseCase,
            ListCustomersUseCaseInterface listCustomersUseCase, GetCustomerByIdUseCaseInterface getCustomerByIdUseCase, UpdateCustomerUseCaseInterface updateCustomerUseCase,
            CustomerPresenterInterface presenter,
            CustomerMapper mapper)
    {
        this.createCustomerUseCase = createCustomerUseCase;
        this.listCustomersUseCase = listCustomersUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.presenter = presenter;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> index() {
        return ResponseEntity.ok(presenter.presentList(listCustomersUseCase.execute()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> indexById(@PathVariable Long id) {
        return ResponseEntity.ok(presenter.present(getCustomerByIdUseCase.execute(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        DomainCustomer customerToCreate = mapper.toDomain(customerRequest);
        DomainCustomer createdCustomer = createCustomerUseCase.execute(customerToCreate);
        return ResponseEntity.ok(presenter.present(createdCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        DomainCustomer customerToUpdate = mapper.toDomain(customerRequest);
        DomainCustomer updatedCustomer = updateCustomerUseCase.execute(customerToUpdate);
        return ResponseEntity.ok(presenter.present(updatedCustomer));
    }
}
