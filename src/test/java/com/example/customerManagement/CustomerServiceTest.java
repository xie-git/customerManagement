package com.example.customerManagement;

import com.example.customerManagement.entity.Customer;
import com.example.customerManagement.repository.CustomerRepository;
import com.example.customerManagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhone("123-456-7890");
    }

    @Test
    public void testCreateCustomer_Success() {
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        when(customerRepository.findByPhone(customer.getPhone())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testCreateCustomer_EmailAlreadyExists() {
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(customer));

        assertEquals("Email is already registered.", exception.getMessage());
        verify(customerRepository, never()).save(customer);
    }

    @Test
    public void testCreateCustomer_PhoneAlreadyExists() {
        when(customerRepository.findByPhone(customer.getPhone())).thenReturn(Optional.of(customer));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(customer));

        assertEquals("Phone is already registered", exception.getMessage());
        verify(customerRepository, never()).save(customer);
    }
}
