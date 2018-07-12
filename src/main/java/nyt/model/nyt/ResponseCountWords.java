package nyt.model.nyt;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseCountWords {

    String keyword;
    String searchKeywords;

    HashMap<String, Integer> finalresult;

    public ResponseCountWords(String keyword, String searchKeywords, HashMap<String, Integer> finalresult) {
        this.keyword = keyword;
        this.searchKeywords = searchKeywords;
        this.finalresult = finalresult;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }


    public HashMap<String, Integer> getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(HashMap<String, Integer> finalresult) {
        this.finalresult = finalresult;
    }
}
