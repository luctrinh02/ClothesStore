package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Cart;

public class CartListener {
	private static Logger log = Logger.getLogger(Cart.class);
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Cart Cart) {
            log.info("About to add/update/delete Cart: " + Cart);
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Cart Cart) {
        log.info("add/update/delete complete for Cart: " + Cart);
    }
    
    @PostLoad
    private void afterLoad(Cart Cart) {
        log.info("Cart loaded from database: " + Cart);
    }
}
