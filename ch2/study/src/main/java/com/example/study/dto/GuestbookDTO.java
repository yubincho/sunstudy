package com.example.study.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestbookDTO {


    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt, modifiedAt;


    public GuestbookDTO(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
