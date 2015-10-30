package org.springframework.data.mybatis.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mybatis.SampleApplication;
import org.springframework.data.mybatis.domain.Customer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringApplicationConfiguration(classes = SampleApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CustomerRepositoryAbc {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testFormat() {
        String queryName = String.format("%s.%s", "org.springframework.data.mybatis.repository", "testFormat");
        System.out.println(queryName);
    }

    @Test
    public void testFindOneCustomer() {
        Customer customer = customerRepository.findOne(100);
        assertNotNull(customer);
    }

    @Test
    public void testFindPage() {
        Map<String, Serializable> map = new HashMap<>();
        map.put("firstName", "John");
        Customer customer = new Customer();
        customer.setFirstName("John");
        Page<Customer> page = this.customerRepository.findAll(new PageRequest(0, 10, Sort.Direction.ASC, "first_name", "last_name"),customer);
        System.out.println(page);
    }

    @Test
    public void testFindAllCustomers() {
//		List<Customer> customers = customerRepository.findAll();
//		assertNotNull(customers);
//		assertTrue(customers.size() > 0);
    }

    @Test
    public void testFindCustomersByFirstName() {
        List<Customer> customers = customerRepository.findByFirstName("John");
        assertNotNull(customers);
        assertTrue(customers.size() == 1);
    }

    @Test
    public void testFindCustomersByLastName() {
        List<Customer> customers = customerRepository.findByLastName("Doe");
        assertNotNull(customers);
        assertTrue(customers.size() == 3);
    }
}
