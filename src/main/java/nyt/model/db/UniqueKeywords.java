package nyt.model.db;

public class UniqueKeywords {
    int id;
    String name;
    String value;
    int rank;
    char major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public char getMajor() {
        return major;
    }

    public void setMajor(char major) {
        this.major = major;
    }
}
