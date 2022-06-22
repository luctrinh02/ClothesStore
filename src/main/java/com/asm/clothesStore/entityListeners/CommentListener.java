package com.asm.clothesStore.entityListeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.asm.clothesStore.entities.Comment;

public class CommentListener {
	private static Logger log = Logger.getLogger(Comment.class);

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyUpdate(Comment Comment) {
		log.info("About to add/update/delete Comment: " + Comment);
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(Comment Comment) {
		log.info("add/update/delete complete for Comment: " + Comment);
	}

	@PostLoad
	private void afterLoad(Comment Comment) {
		log.info("Comment loaded from database: " + Comment);
	}
}
