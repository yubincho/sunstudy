package com.example.study.controller;

import com.example.study.dto.GuestbookDTO;
import com.example.study.dto.PageRequestDTO;
import com.example.study.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;


    @GetMapping("/")
    public String index() {

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list......" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }


    @GetMapping("/register/new")
    public String registerForm() {
        log.info("register get..");
//        model.addAttribute("request", new GuestbookDTO());
        return "guestbook/register";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @PostMapping("/register")
    public String register(GuestbookDTO dto, RedirectAttributes redirectAttributes){

//        log.info("dto..." + dto);
//        GuestbookDTO dto = new GuestbookDTO();
//        dto.setTitle(title);
//        dto.setContent(content);
//        dto.setWriter(writer);

        log.info("dto..." + dto);

        Long gno = service.register(dto);

//        try{
//            if (gno == null) throw new Exception("Write failed!");
//
//            redirectAttributes.addFlashAttribute("msg", gno);
//            return "redirect:/guestbook/list";
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("msg", "WRITE_ERROR.");
//            return "redirect:/guestbook/register";
//        }
        redirectAttributes.addFlashAttribute("msg", gno);
            return "redirect:/guestbook/list";

    }
//  m.addAttribute("msg", "잘 삭제되었습니다."); -> 브라우저에 메시지 여러번 출력되는 현상 발생




}
