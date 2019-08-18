package com.adhokshaja.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adhokshaja.web.domain.DomainObject;

public abstract class AbstractMapService {

	protected Map<Integer, DomainObject> domainMap;

	public AbstractMapService() {
		domainMap = new HashMap<>();
		loadDomainObject();
	}

	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values());
	}

	public DomainObject getById(Integer id) {
		return domainMap.get(id);
	}

	public DomainObject saveOrUpdate(DomainObject domainObject) {
		if (domainObject != null) {
			if (domainObject.getId() == null) {
				domainObject.setId(getNextKey());
			}
			domainMap.put(domainObject.getId(), domainObject);
			return domainObject;
		} else {
			throw new RuntimeException("Obejct can't be null");
		}
	}
	
	public void delete(Integer id) {
        domainMap.remove(id);
    }

	private Integer getNextKey() {
		return Collections.max(domainMap.keySet()) + 1;
	}

	protected abstract void loadDomainObject();

}
