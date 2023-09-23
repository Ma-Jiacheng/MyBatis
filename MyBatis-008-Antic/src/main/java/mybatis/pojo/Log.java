package mybatis.pojo;

public class Log {
    private Integer id;
    private String log;
    private String datetime;

    public Log() {
    }

    public Log(Integer id, String log, String datetime) {
        this.id = id;
        this.log = log;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", log='" + log + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
