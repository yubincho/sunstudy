package com.example.study.repository;

import com.example.study.entity.GuestBook;
import com.example.study.entity.QGuestBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class GuestBookRepositoryTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void init() {    queryFactory = new JPAQueryFactory(em);}

    @Autowired
    private GuestBookRepository guestBookRepository;


//    @BeforeEach
    @Test
    @Transactional
    public void insertDummies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            GuestBook guestBook = GuestBook.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .writer("user" + (i % 10))
                    .build();

            System.out.println(guestBookRepository.save(guestBook));


        });

    }


    @Test
    public void updateTest() {

        Optional<GuestBook> result = guestBookRepository.findById(100L);

        if (result.isPresent()) {

            GuestBook guestBook = result.get();

            guestBook.changeTitle("Changed Title..");
            guestBook.changeContent("Changed Content..");

            guestBookRepository.save(guestBook);


        }

    }


    @Test
    public void startQuerydsl() {

        QGuestBook guestbook = new QGuestBook("guestbook");

        GuestBook findTitle = queryFactory
                .select(guestbook)
                .from(guestbook)
                .where(guestbook.title.eq("Title..3"))
                .fetchOne();

        assertThat(findTitle.getTitle()).isEqualTo("Title..3");


    }







}