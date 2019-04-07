package de.clumsystuff.spring.caching.example;

import java.util.function.Supplier;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class DataObjectRepository {

	private Supplier<String> expansiveResourceSupplier;

	@Cacheable("dataObjectCache")
	public DataObject getDataObject() {

		this.expansiveResourceSupplier.get();
		return new DataObject("Tony", "Test");
	}

	public void setExpansiveResourceSupplier(Supplier<String> expansiveResourceSupplier) {
		this.expansiveResourceSupplier = expansiveResourceSupplier;
	}
}
