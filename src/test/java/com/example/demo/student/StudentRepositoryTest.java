package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // given
        Student student = new Student(
                "Nikolay",
                "forsh_nikolay@mail.ru",
                Gender.MALE
        );
        underTest.save(student);
        // when
        boolean expected = underTest.selectExistsEmail("forsh_nikolay@mail.ru");

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void ifStudentEmailDoesNotExist() {
        // given
        String email = "forsh_nikolay@mail.ru";
        // when
        boolean expected = underTest.selectExistsEmail(email);
        // then
        assertThat(expected).isFalse();
    }
}