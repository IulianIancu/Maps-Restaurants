package kalpesh.mac.com.raandroid_header.model;

/**
 * Created by kalpesh on 06/07/2016.
 */

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Embedded {

    @Expose
    private List<Subscription> subscriptions = new ArrayList<Subscription>();

    /**
     *
     * @return
     *     The subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     *
     * @param subscriptions
     *     The subscriptions
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}