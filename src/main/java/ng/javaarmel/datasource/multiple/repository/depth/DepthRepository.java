package ng.javaarmel.datasource.multiple.repository.depth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.javaarmel.datasource.multiple.entity.depth.Department;
@Repository
public interface DepthRepository extends JpaRepository<Department, Integer> {

}
