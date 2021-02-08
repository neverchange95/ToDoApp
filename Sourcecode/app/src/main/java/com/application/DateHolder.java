package com.application;

public class DateHolder {
    private DateHandler dateHandler;

    public DateHolder() {
        if (this.dateHandler == null) {
            this.dateHandler = new DateHandler();
        }
    }

    public DateHandler getDateHandler() {
        return dateHandler;
    }
}
