package ibf2022.batch2.csf.backend.models;

public class Record {
    private String bundleId;
    private String date;
    private String title;
    private String name;
    private String comments;
    private String urls;
    
    public String getBundleId() {
        return bundleId;
    }
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getUrls() {
        return urls;
    }
    public void setUrls(String urls) {
        this.urls = urls;
    }
    
    // public static Record create(Document d){
    //     Record r = new Record();
    //     r.setBundleId(d.getString("bundleId"));
    //     r.setDate(d.getString("date"));
    //     r.setBundleId(d.getString("bundleId"));
    //     r.setBundleId(d.getString("bundleId"));
    //     r.setBundleId(d.getString("bundleId"));
    // }
}
