package media;


public class SoundBean {
    private String filePath;
    private String addDate;

    public SoundBean() {
    }

    public SoundBean(String filePath, String addDate) {
        this.filePath = filePath;
        this.addDate = addDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}
