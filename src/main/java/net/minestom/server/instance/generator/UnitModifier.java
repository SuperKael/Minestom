package net.minestom.server.instance.generator;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;

public interface UnitModifier extends Block.Setter {
    void setRelative(int x, int y, int z, @NotNull Block block);

    void setAll(@NotNull Supplier supplier);

    void fill(@NotNull Block block);

    void fill(@NotNull Point start, @NotNull Point end, @NotNull Block block);

    interface Supplier {
        @NotNull Block get(int x, int y, int z);
    }
}