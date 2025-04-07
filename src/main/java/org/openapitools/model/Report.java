package org.openapitools.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.openapitools.enums.ReportStatus;

@Document("reportes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
    @DBRef
    private GeoJsonPoint location;
    private List<String> categories;
    private ObjectId idUser;
    private ReportStatus status;
    private int ratingsImportant;
}
