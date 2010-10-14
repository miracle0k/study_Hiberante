package com.oreilly.hh;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface Test {
	@Transactional(readOnly=false,
			propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.SERIALIZABLE,
			rollbackFor={NumberFormatException.class},
			timeout=1)
	public abstract void run();
}
