package com.federicogualdi.shipmenthub.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class InternalDate implements Serializable {

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public static InternalDate Create() {
        InternalDate internalDate = new InternalDate();
        LocalDateTime now = LocalDateTime.now();
        internalDate.setCreatedOn(now);
        internalDate.setUpdatedOn(now);
        return internalDate;
    }

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "InternalDate{" + "createdOn=" + createdOn + ", updatedOn=" + updatedOn + '}';
    }

}
