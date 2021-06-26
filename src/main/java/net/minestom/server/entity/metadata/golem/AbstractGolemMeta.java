package net.minestom.server.entity.metadata.golem;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.metadata.MobMeta;
import net.minestom.server.entity.metadata.PathfinderMobMeta;
import org.jetbrains.annotations.NotNull;

public class AbstractGolemMeta extends PathfinderMobMeta {
    public static final byte OFFSET = PathfinderMobMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    protected AbstractGolemMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

}