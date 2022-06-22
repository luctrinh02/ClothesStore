package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Detailbill;

public class DetailBillListener {
	private static Logger log = Logger.getLogger(Detailbill.class);

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyUpdate(Detailbill Detailbill) {
		log.info("About to add/update/delete Detailbill: " + Detailbill);
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(Detailbill Detailbill) {
		log.info("add/update/delete complete for Detailbill: " + Detailbill);
	}

	@PostLoad
	private void afterLoad(Detailbill Detailbill) {
		log.info("Detailbill loaded from database: " + Detailbill);
	}

}
