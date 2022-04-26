package ng.javaarmel.datasource.multiple.entity.eng;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engineer {
	@Id
	private int id;
	private String name;
	private String password;

}