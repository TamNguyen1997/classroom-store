package classroom.store.configuration;

import classroom.store.orm.ClassRoomDb;
import lombok.experimental.UtilityClass;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class EntityConverter {
    public Converter<ClassRoomDb, UUID> convertTeacherEntityToId() {
        return ctx ->
                Optional.of(ctx)
                        .map(MappingContext::getSource)
                        .map(ClassRoomDb::getId)
                        .orElse(null);
    }
}
