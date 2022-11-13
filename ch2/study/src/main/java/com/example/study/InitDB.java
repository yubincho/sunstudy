package com.example.study;

import com.example.study.dto.GuestbookDTO;
import com.example.study.entity.GuestBook;
import com.example.study.repository.GuestBookRepository;
import com.example.study.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;


@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        @Autowired
        private GuestBookRepository guestBookRepository;

        @Autowired
        private GuestbookService guestbookService;

        public void dbInit() {

            IntStream.rangeClosed(1,101).forEach(i -> {

                GuestBook guestBook = GuestBook.builder()
                        .title("Title.." + i)
                        .content("Content.." + i)
                        .writer("user" + (i % 10))
                        .build();

                guestBookRepository.save(guestBook);


            });




        }


    }



}


