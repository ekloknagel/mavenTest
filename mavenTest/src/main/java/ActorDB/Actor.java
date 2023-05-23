package ActorDB;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
   private Integer actor_id;
   private String first_name;
   private String last_name;
   private Date last_update;
  
	public Actor(String first_name, String last_name, Date last_update) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}


}

