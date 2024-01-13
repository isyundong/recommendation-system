package com.isyundong.recommend.orchestrate.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class RecommendController {


    @PostMapping("/recommend")
    public void recommend(){
        // todo yundong
    }

}
