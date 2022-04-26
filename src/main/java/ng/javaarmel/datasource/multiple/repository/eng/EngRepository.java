package ng.javaarmel.datasource.multiple.repository.eng;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.javaarmel.datasource.multiple.entity.eng.Engineer;
@Repository
public interface EngRepository extends JpaRepository<Engineer, Integer> {

}
