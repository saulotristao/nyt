package nyt.mappers;


import nyt.model.db.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NYTMapper {

    String INSERT_ARTICLE = "INSERT INTO `nyt`.`articles` (`headline`, `snippet`," +
            " `url`, `pub_date`) VALUES (#{headline}, #{snippet}, #{url}, #{pub_date})";

    @Insert(INSERT_ARTICLE)
    public int insertRecord (Article article);

}
