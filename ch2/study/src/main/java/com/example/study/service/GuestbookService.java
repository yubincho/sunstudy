package com.example.study.service;


import com.example.study.dto.GuestbookDTO;
import com.example.study.dto.PageRequestDTO;
import com.example.study.dto.PageResultDTO;
import com.example.study.entity.GuestBook;
import com.example.study.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class GuestbookService {

    private final GuestBookRepository repository;

    public Long register(GuestbookDTO dto) {
        log.info(dto);
        GuestBook entity = dtoToEntity(dto);

        log.info(entity);
        return entity.getGno();
    }


    public GuestBook dtoToEntity(GuestbookDTO dto) {

        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }


    public GuestbookDTO entityToDto(GuestBook entity) {

        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();

        return dto;

    }


    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = repository.findAll(pageable);

        Function<GuestBook, GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }









}
