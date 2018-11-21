package com.xiia.indicatori.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Updater {

	    @CreatedDate
	    @Column(name = "created_at", nullable = false)
	    private Date createdAt;

	    @LastModifiedDate
	    @Column(name = "updated_at", nullable = false)
	    private Date updatedAt;

	    @LastModifiedBy
	    @Column(name = "updated_by", nullable = false, length = 50)
	    private Integer updatedBy;
	    
	    public void setUpdater(Integer user) {
	        Date now = new Date();
	        try {
	        	if (getCreatedAt() == null) setCreatedAt(now);
	        } catch (Exception e) {
	        	setCreatedAt(now);
	        }
	        setUpdatedAt(now);
	        setUpdatedBy(user);
	    }

}
