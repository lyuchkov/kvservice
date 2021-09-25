package ru.lyuchkov.kvservice.entity;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

public class Data<T> implements Serializable {
    private T value;
    private Date endDate;
    @Value("#{new Long('${default.ttl}')}")
    private long defaultTtlSeconds;


    public Data(T value, long timeToLifeSeconds) {
        this.value = value;
        setTimeToLife(timeToLifeSeconds);
        configureDefaultTtl();
    }

    public Data(T value) {
        this.value = value;
        setTimeToLife();
        configureDefaultTtl();
    }
    private void configureDefaultTtl(){
        if(defaultTtlSeconds==0) defaultTtlSeconds = 60;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        setTimeToLife();
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setValue(T value, long timeToLifeSeconds) {
        this.value = value;
        setTimeToLife(timeToLifeSeconds);
    }

    private void setTimeToLife(long timeToLifeSeconds) {
        this.endDate = new Date(new Date().getTime() + timeToLifeSeconds * 1000);
    }

    private void setTimeToLife() {
        this.endDate = new Date(new Date().getTime() + defaultTtlSeconds * 1000);
    }

}