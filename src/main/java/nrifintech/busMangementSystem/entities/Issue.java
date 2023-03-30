package nrifintech.busMangementSystem.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

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
    @Column(length=10000)
    String issue;
    Date date;
    
    int isResolved=0;

}

