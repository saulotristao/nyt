package nyt.controllers;

import nyt.model.nyt.NYTRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nyt.service.NYTService;

@RestController
@RequestMapping("/nyt")
public class Controllers {

    @Autowired
    NYTService service;


    @RequestMapping("/search")
    public NYTRoot searchNYT (
            @RequestParam ("keyword") String keyword,
            @RequestParam("persist")String persist ){

        NYTRoot response = service.searchNYT(keyword, persist);

        return response;

    }

}
