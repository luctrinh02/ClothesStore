package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Category;

public class CategoryListener {
	private static Logger log = Logger.getLogger(Category.class);
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Category Category) {
        if (Category.getId() == null) {
            log.info("About to add a Category");
        } else {
            log.info("About to update/delete Category: " + Category);
        }
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Category Category) {
        log.info("add/update/delete complete for Category: " + Category);
    }
    
    @PostLoad
    private void afterLoad(Category Category) {
        log.info("Category loaded from database: " + Category);
    }
}
