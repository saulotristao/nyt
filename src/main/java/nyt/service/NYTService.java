package nyt.service;

import nyt.mappers.NYTMapper;
import nyt.model.db.Article;
import nyt.model.db.UniqueKeywords;
import nyt.model.nyt.Docs;
import nyt.model.nyt.Keywords;
import nyt.model.nyt.NYTRoot;
import nyt.model.nyt.ResponseCountWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NYTService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NYTMapper mapper;

    private final String API_KEY = "4831cb5ac3e34a2d83974365787fe708";


    public NYTRoot searchNYT(String keyword, String persist, String map) {

        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q="+keyword+"&api-key="+API_KEY;

        NYTRoot response = restTemplate.getForObject(url, NYTRoot.class);

        if( persist.equalsIgnoreCase("true")){
            persistResults(response);
        }

        if( map.equalsIgnoreCase("true")){
            mapAllKeyWordsForkeyword(response);
        }

        return response;
    }



    /**
     * mapping the results of the api into the database
     * @param data
     */
    private void persistResults(NYTRoot data) {

        // for each element of the response call insert
        for (Docs doc : data.getResponse().getDocs()){
            // creating a temp
            Article a = new Article();

            a.setHeadline(doc.getHeadline().getMain());

            a.setPub_date(doc.getPub_date());
            a.setSnippet(doc.getSnippet());
            a.setUrl(doc.getWeb_url());

            // calling insert
            mapper.insertRecord(a);
        }

    }

    private void mapAllKeyWordsForkeyword(NYTRoot data) {

        for (Docs doc : data.getResponse().getDocs()){
            for(Keywords key : doc.getKeywords()){
                    UniqueKeywords temp = new UniqueKeywords();
                    temp.setName(key.getName());
                    temp.setRank(key.getRank());
                    temp.setValue(key.getValue());
                    temp.setMajor(key.getMajor());
                    try {
                        mapper.insertKeyword(temp);
                    }catch (Exception e){
                        System.out.println("Duplicated detected " + temp.getName());
                    }
                }

        }

    }

    public ArrayList<String> goAndFind(String keyword) {
        String preparedkeyword = "%"+keyword+"%";
        Article[] temp = mapper.searchInDB(preparedkeyword);
        if (temp.length == 0){
            searchNYT(keyword,"true","false");
            temp = mapper.searchInDB(preparedkeyword);
        }

        ArrayList<String> response = new ArrayList<>();
        for (Article a : temp){
            response.add(a.getHeadline());
        }
        return response;
    }

    public ResponseCountWords countWords(String keyword, String searchWords) {
        searchNYT(keyword,"true","false");
        String[] words = searchWords.split(",");
        HashMap<String, Integer> response = new HashMap<>();
        for (String word : words){
            int c = countPerWord(word);
            response.put(word,c);
        }
        ResponseCountWords finalresponse = new ResponseCountWords(keyword,searchWords,response);
        return finalresponse;
    }

    private int countPerWord(String word) {
        ArrayList<String> stringsWithWord = goAndFind(word);
        int counter = 0;
        for (String s : stringsWithWord){
            s = StringUtils.lowerCase(s);
            if(StringUtils.countMatches(s,word) != 0){
                counter++;
            }
        }
        return counter;


    }
}

