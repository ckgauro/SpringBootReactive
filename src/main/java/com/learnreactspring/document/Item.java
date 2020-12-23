package com.learnreactspring.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Chandra
 */

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    private String id;
    private String description;
    private Double price;

}
