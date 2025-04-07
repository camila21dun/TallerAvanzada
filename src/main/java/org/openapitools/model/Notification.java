package org.openapitools.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;

@Document("notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime date;
    private String message;
    private ObjectId idUser;
    private GeoJsonPoint location;

}