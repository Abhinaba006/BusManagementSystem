package nrifintech.busMangementSystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class Issue {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    int user_id;
    String issue;
    int isResolved;

}

