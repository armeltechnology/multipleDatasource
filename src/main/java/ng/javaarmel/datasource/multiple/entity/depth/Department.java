package ng.javaarmel.datasource.multiple.entity.depth;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	@Id
	private int id;
	private String nameDepth;
	private int numberOfStaff;
	

}
