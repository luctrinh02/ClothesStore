package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Bill;

public class BillListener {
	private static Logger log = Logger.getLogger(Bill.class);
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Bill bill) {
        if (bill.getId() == null) {
            log.info("About to add a bill");
        } else {
            log.info("About to update/delete bill: " + bill);
        }
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Bill bill) {
        log.info("add/update/delete complete for bill: " + bill);
    }
    
    @PostLoad
    private void afterLoad(Bill bill) {
        log.info("bill loaded from database: " + bill);
    }
}
