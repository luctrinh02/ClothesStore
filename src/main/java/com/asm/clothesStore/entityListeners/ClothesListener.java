package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Clothes;

public class ClothesListener {
	private static Logger log = Logger.getLogger(Clothes.class);
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Clothes Clothes) {
        if (Clothes.getId() == null) {
            log.info("About to add a Product");
        } else {
            log.info("About to update/delete Product: " + Clothes);
        }
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Clothes Clothes) {
        log.info("add/update/delete complete for Product: " + Clothes);
    }
    
    @PostLoad
    private void afterLoad(Clothes Clothes) {
        log.info("Product loaded from database: " + Clothes);
    }
}
