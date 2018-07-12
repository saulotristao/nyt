package nyt.mappers;


import nyt.model.db.Article;
import nyt.model.db.UniqueKeywords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NYTMapper {

    String INSERT_ARTICLE = "INSERT INTO `nyt`.`articles` (`headline`, `snippet`," +
            " `url`, `pub_date`) VALUES (#{headline}, #{snippet}, #{url}, #{pub_date})";

    String INSERT_KEYWORD = "INSERT INTO `nyt`.`uniqueKeywords` (`name`, `value`, `rank`, `major`) VALUES (#{name}, #{value}, #{rank}, #{major})";

    String FIND_KEYWORD = "SELECT * FROM nyt.articles where snippet like #{keyword}";

    @Insert(INSERT_ARTICLE)
    public int insertRecord (Article article);

    @Insert(INSERT_KEYWORD)
    public int insertKeyword (UniqueKeywords keywords);


    @Select(FIND_KEYWORD)
    public Article[] searchInDB(String keyword);
}
