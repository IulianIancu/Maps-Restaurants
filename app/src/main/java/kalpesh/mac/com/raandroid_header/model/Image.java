package kalpesh.mac.com.raandroid_header.model;

import com.google.gson.annotations.Expose;

public class Image {

    @Expose
    private String image;
    @Expose
    private String icon;

    /**
     *
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

}