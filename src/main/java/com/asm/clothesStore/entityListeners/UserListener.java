package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.User;

public class UserListener {
	private static Logger log = Logger.getLogger(User.class);
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(User User) {
        if (User.getId() == null) {
            log.info("About to add a User");
        } else {
            log.info("About to update/delete User: " + User);
        }
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(User User) {
        log.info("add/update/delete complete for User: " + User);
    }
    
    @PostLoad
    private void afterLoad(User User) {
        log.info("User loaded from database: " + User);
    }
}
