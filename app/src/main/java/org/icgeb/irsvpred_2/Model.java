package org.icgeb.irsvpred_2;

public class Model {

    private String direction;
    private int image;
    private String title;
    private String desc;
    private String rdirection;

    public Model(int image, String title, String desc, String direction, String rdirection) {
        this.direction = direction;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.rdirection = rdirection;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public  String getRdirection() {return rdirection;}
    public void setRdirection(String rdirection) {this.rdirection = rdirection;}

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
