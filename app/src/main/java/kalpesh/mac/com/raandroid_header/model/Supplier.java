package kalpesh.mac.com.raandroid_header.model;

import com.google.gson.annotations.Expose;

public class Supplier {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Image image;

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
