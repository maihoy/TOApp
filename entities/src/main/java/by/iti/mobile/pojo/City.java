package by.iti.mobile.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "city")
public class City extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    String name;


}
