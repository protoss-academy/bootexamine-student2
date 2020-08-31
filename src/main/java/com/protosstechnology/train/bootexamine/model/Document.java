package com.protosstechnology.train.bootexamine.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by nitchpon.t on 31/8/2563.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Document {
    private @Id @GeneratedValue Long id;
    String documentNumber;
    String description;
}
