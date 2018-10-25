package com.pchromic.Repository;

import com.pchromic.Entity.Customer;
import com.pchromic.Entity.EntityBuilder.CustomerBuilder;
import static org.junit.Assert.*;

import com.pchromic.Entity.Rental;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class CustomerRepositoryImplTest {

    private static Validator validator;

    @Autowired
    private CustomerRepository repository;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldAddCustomer(){
        // given
        Customer customer = CustomerBuilder.customerBuilder()
                .withFirstName("Alicja")
                .withLastName("Flinta")
                .withAddress("Kozia wolka")
                .withBirthDate(LocalDate.of(1992,12,22))
                .withCreditCardNumber("378282246310005")
                .withEmail("przemek1992@wp.pl")
                .withPhoneNumber("123345657")
                .build();
        // when
        int repoSizeBefore = repository.findAll().size();
        repository.save(customer);
        int repoSizeAfter = repository.findAll().size();

        // then
        assertEquals(repoSizeBefore+1,repoSizeAfter);

    }

    @Test
    public void shouldSetConstraintValidationWhenBirthDateNotGiven(){
        // given
        Customer customer = CustomerBuilder.customerBuilder()
                .withFirstName("Alicja")
                .withLastName("Flinta")
                .withAddress("Kozia wolka")
                .withCreditCardNumber("378282246310005")
                .withEmail("przemek1992@wp.pl")
                .withPhoneNumber("123345657")
                .build();
        // when
        Set<ConstraintViolation<Customer>> constraintViolations =
                validator.validate( customer );

        // then
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "Birth date is mandatory",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void shouldSetConstraintValidationWhenPhoneNumberTooLong(){
        // given
        Customer customer = CustomerBuilder.customerBuilder()
                .withFirstName("Alicja")
                .withLastName("Flinta")
                .withAddress("Kozia wolka")
                .withCreditCardNumber("378282246310005")
                .withBirthDate(LocalDate.of(1992,12,22))
                .withEmail("przemek1992@wp.pl")
                .withPhoneNumber("123345657123345")
                .build();
        // when
        Set<ConstraintViolation<Customer>> constraintViolations =
                validator.validate( customer );

        // then
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "Please write number in format like: 123 456 789",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void shouldSetConstraintValidationWhenPhoneNumberContainsNonNumeric(){
        // given
        Customer customer = CustomerBuilder.customerBuilder()
                .withFirstName("Alicja")
                .withLastName("Flinta")
                .withAddress("Kozia wolka")
                .withCreditCardNumber("378282246310005")
                .withBirthDate(LocalDate.of(1992,12,22))
                .withEmail("przemek1992@wp.pl")
                .withPhoneNumber("abcdefrgty")
                .build();
        // when
        Set<ConstraintViolation<Customer>> constraintViolations =
                validator.validate( customer );

        // then
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be a valid phone number.Found: "+customer.getPhoneNumber(),
                constraintViolations.iterator().next().getMessage()
        );
    }

}
