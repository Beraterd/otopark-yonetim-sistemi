package com.hgs.gecis.hgs_gecis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgs.gecis.hgs_gecis.service.GecisService;

@RestController
@RequestMapping("/api/gecis")
public class GecisController {

    @Autowired
    private GecisService gecisService;

    @PostMapping("/yap/{userId}")
    public String processGecis(@PathVariable Long userId,
                               @RequestParam Long ilkGiseId,
                               @RequestParam Long sonGiseId,
                               @RequestParam String plaka) {
        return gecisService.processGecis(userId, ilkGiseId, sonGiseId, plaka);
    }
}
