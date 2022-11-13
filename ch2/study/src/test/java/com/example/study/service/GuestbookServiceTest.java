package com.example.study.service;

import com.example.study.dto.GuestbookDTO;
import com.example.study.dto.PageRequestDTO;
import com.example.study.dto.PageResultDTO;
import com.example.study.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService guestbookService;


    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title")
                .content("sample content")
                .writer("user0")
                .build();

        System.out.println(guestbookService.register(guestbookDTO));
    }


    @Test
    void testList1() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, GuestBook> resultDTO = guestbookService.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

    }

    @Test
    void testList2() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, GuestBook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("PREV" + resultDTO.isPrev());
        System.out.println("NEXT" + resultDTO.isNext());
        System.out.println("TOTAL" + resultDTO.getTotalPage());

        System.out.println("-------------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("-------------------------------------------");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }







}