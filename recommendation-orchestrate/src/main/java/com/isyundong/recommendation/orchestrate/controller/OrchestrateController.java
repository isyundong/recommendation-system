package com.isyundong.recommendation.orchestrate.controller;


import com.isyundong.recommendation.orchestrate.domain.req.OrchestrateRequest;
import com.isyundong.recommendation.orchestrate.domain.resp.OrchestrateResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class OrchestrateController {

    @PostMapping("/recommend")
    public OrchestrateResponse recommend(@RequestBody OrchestrateRequest req) {


        return null;
    }

}
