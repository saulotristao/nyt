package nyt.service;

import nyt.mappers.NYTMapper;
import nyt.model.db.Article;
import nyt.model.nyt.Docs;
import nyt.model.nyt.NYTRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NYTService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NYTMapper mapper;

    private final String API_KEY = "4831cb5ac3e34a2d83974365787fe708";


    public NYTRoot searchNYT(String keyword, String persist) {

        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q="+keyword+"&api-key="+API_KEY;

        NYTRoot response = restTemplate.getForObject(url, NYTRoot.class);

        if( persist.equalsIgnoreCase("true")){
            persistResults(response);
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
}

