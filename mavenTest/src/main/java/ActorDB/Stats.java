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
public class Stats {
    @Getter @Setter private Double max_amt;
    @Getter @Setter private Double min_amt;
    @Getter @Setter private Double avg_amt;
    @Getter @Setter private Integer num_payments;
    
}

