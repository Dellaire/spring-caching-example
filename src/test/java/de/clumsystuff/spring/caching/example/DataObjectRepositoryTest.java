package de.clumsystuff.spring.caching.example;

import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataObjectRepositoryTest {

	@Autowired
	private DataObjectRepository dataObjectRepository;

	@Test
	public void shortCache() throws InterruptedException {

		@SuppressWarnings("unchecked")
		Supplier<String> expansiveResourceSupplier = Mockito.mock(Supplier.class);
		this.dataObjectRepository.setExpansiveResourceSupplier(expansiveResourceSupplier);

		this.dataObjectRepository.getLongDataObject();
		Thread.sleep(2000);
		this.dataObjectRepository.getLongDataObject();

		Mockito.verify(expansiveResourceSupplier, Mockito.times(1)).get();
	}

	@Test
	public void longCache() throws InterruptedException {

		@SuppressWarnings("unchecked")
		Supplier<String> expansiveResourceSupplier = Mockito.mock(Supplier.class);
		this.dataObjectRepository.setExpansiveResourceSupplier(expansiveResourceSupplier);

		this.dataObjectRepository.getShortDataObject();
		Thread.sleep(2000);
		this.dataObjectRepository.getShortDataObject();

		Mockito.verify(expansiveResourceSupplier, Mockito.times(2)).get();
	}
}
