package org.alexcoders.imageGalleryUploader.models;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QImageDescription is a Querydsl query type for ImageDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QImageDescription extends EntityPathBase<ImageDescription> {

    private static final long serialVersionUID = -1191564812L;

    public static final QImageDescription imageDescription = new QImageDescription("imageDescription");

    public final StringPath altTag = createString("altTag");

    public final StringPath caption = createString("caption");

    public final BooleanPath defaultName = createBoolean("defaultName");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public QImageDescription(String variable) {
        super(ImageDescription.class, forVariable(variable));
    }

    public QImageDescription(Path<? extends ImageDescription> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageDescription(PathMetadata<?> metadata) {
        super(ImageDescription.class, metadata);
    }

}

