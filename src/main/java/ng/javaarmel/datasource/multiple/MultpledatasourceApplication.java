package ng.javaarmel.datasource.multiple;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ng.javaarmel.datasource.multiple.entity.depth.Department;
import ng.javaarmel.datasource.multiple.entity.eng.Engineer;
import ng.javaarmel.datasource.multiple.repository.depth.DepthRepository;
import ng.javaarmel.datasource.multiple.repository.eng.EngRepository;

@SpringBootApplication
public class MultpledatasourceApplication implements CommandLineRunner{
	@Autowired
	private DepthRepository depthRepo;
	@Autowired
	private EngRepository enRepo;

	public static void main(String[] args) {
		SpringApplication.run(MultpledatasourceApplication.class, args);
		
	}

	

	@Override
	public void run(String... args) throws Exception {
		depthRepo.save(new Department(7,"juliba",102));
		depthRepo.saveAll(Stream.of(new Department(1,"Technical",18),new Department(6,"BSSdivision",15),new Department(2,"Radio",14)).collect(Collectors.toList()));
		enRepo.saveAll(Stream.of(new Engineer(1,"Ngounou","12345"),new Engineer(2,"Nathan","1234567")).collect(Collectors.toList()));
		
	}

}
