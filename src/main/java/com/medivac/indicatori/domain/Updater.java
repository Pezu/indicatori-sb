package com.medivac.indicatori.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Updater {

	    @CreatedDate
	    @Column(name = "created_at", nullable = false)
	    private Date creationAt;

	    @LastModifiedDate
	    @Column(name = "updated_at", nullable = false)
	    private Date updatedAt;

	    @LastModifiedBy
	    @Column(name = "updated_by", nullable = false, length = 50)
	    private Long updatedBy;
	    
	    public void setUpdater(Long user) {
	        Date now = new Date();
	        try {
	        	getCreationAt();
	        } catch (Exception e) {
	        	setCreationAt(now);
	        }
	        setUpdatedAt(now);
	        setUpdatedBy(user);
	    }


	


}
