package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {
    @RequestMapping("/chinhsach")
    public String chinhSach() {
        return "chinhsach";
    }
    @RequestMapping("/thongtin")
    public String thongtinPage() {

        return "thongtin";
    }
}
