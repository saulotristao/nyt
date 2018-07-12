package nyt.controllers;

import nyt.model.nyt.NYTRoot;
import nyt.model.nyt.ResponseCountWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nyt.service.NYTService;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/nyt")
public class Controllers {

    @Autowired
    NYTService service;


    @RequestMapping("/search")
    public NYTRoot searchNYT (
            @RequestParam ("keyword") String keyword,
            @RequestParam("persist")String persist,
            @RequestParam("map") String map){

        NYTRoot response = service.searchNYT(keyword, persist, map);

        return response;
    }

    @GetMapping("/find")
    public ArrayList<String> findkeyword (@RequestParam ("keyword") String keyword){
        return service.goAndFind(keyword);
    }

    @GetMapping("/search")
    public ResponseCountWords countkeyword (@RequestParam ("keyword") String keyword,
                                                 @RequestParam ("searchWords") String searchWords){
        return service.countWords(keyword, searchWords);
    }

//    create an API call that will take three arguments "keyword", "searchWords" and "useDb" (useDb will be "true" or "false")
//            - just FYI, searchWords is a comma separated list of words that are provided in the url
//              - they will land in your method as an array
//     - if useDb is true - return a response that shows the total number of times each keyword appears in the database, and how many distinct articles it appears in
//     - is useDb is false - make a call to the NYT api and scan through the entire results and count the number of times each word appears in the NYT API result using a HashMap

}
