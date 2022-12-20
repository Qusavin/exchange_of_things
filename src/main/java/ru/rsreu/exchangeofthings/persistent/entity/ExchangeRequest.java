package ru.rsreu.exchangeofthings.persistent.entity;

import java.util.Date;

/**
 * Exchange request entity.
 */
public class ExchangeRequest {
    private Integer id;
    private Item receiverItem;
    private Item senderItem;
    private String status;
    private Date date;

    public ExchangeRequest(Item receiverItem, Item senderItem, String status) {
        this.receiverItem = receiverItem;
        this.senderItem = senderItem;
        this.status = status;
    }

    public ExchangeRequest(Integer id, Item receiverItem, Item senderItem, String status, Date date) {
        this.id = id;
        this.receiverItem = receiverItem;
        this.senderItem = senderItem;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getReceiverItem() {
        return receiverItem;
    }

    public void setReceiverItem(Item receiverItem) {
        this.receiverItem = receiverItem;
    }

    public Item getSenderItem() {
        return senderItem;
    }

    public void setSenderItem(Item senderItem) {
        this.senderItem = senderItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
