package kalpesh.mac.com.raandroid_header.model;

import com.google.gson.annotations.Expose;

public class Subscription {

    @Expose
    private String id;
    @Expose
    private String type;
    @Expose
    private Integer length;
    @Expose
    private String lengthString;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String appstoreProductId;
    @Expose
    private String previewUrl;
    @Expose
    private String coverageUrl;
    @Expose
    private Supplier supplier;
    @Expose
    private Links_ links;
    @Expose
    private Integer credits;

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The length
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length
     *     The length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     *
     * @return
     *     The lengthString
     */
    public String getLengthString() {
        return lengthString;
    }

    /**
     *
     * @param lengthString
     *     The lengthString
     */
    public void setLengthString(String lengthString) {
        this.lengthString = lengthString;
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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The appstoreProductId
     */
    public String getAppstoreProductId() {
        return appstoreProductId;
    }

    /**
     *
     * @param appstoreProductId
     *     The appstoreProductId
     */
    public void setAppstoreProductId(String appstoreProductId) {
        this.appstoreProductId = appstoreProductId;
    }

    /**
     *
     * @return
     *     The previewUrl
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     *
     * @param previewUrl
     *     The previewUrl
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     *
     * @return
     *     The coverageUrl
     */
    public String getCoverageUrl() {
        return coverageUrl;
    }

    /**
     *
     * @param coverageUrl
     *     The coverageUrl
     */
    public void setCoverageUrl(String coverageUrl) {
        this.coverageUrl = coverageUrl;
    }

    /**
     *
     * @return
     *     The supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     *
     * @param supplier
     *     The supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     *
     * @return
     *     The links
     */
    public Links_ getLinks() {
        return links;
    }

    /**
     *
     * @param links
     *     The _links
     */
    public void setLinks(Links_ links) {
        this.links = links;
    }

    /**
     *
     * @return
     *     The credits
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     *
     * @param credits
     *     The credits
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

}