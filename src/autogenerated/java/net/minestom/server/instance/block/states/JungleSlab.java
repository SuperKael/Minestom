package net.minestom.server.instance.block.states;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockAlternative;

/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(
        since = "forever",
        forRemoval = false
)
public final class JungleSlab {
    /**
     * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
     */
    @Deprecated(
            since = "forever",
            forRemoval = false
    )
    public static void initStates() {
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8568, "type=top", "waterlogged=true"));
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8569, "type=top", "waterlogged=false"));
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8570, "type=bottom", "waterlogged=true"));
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8571, "type=bottom", "waterlogged=false"));
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8572, "type=double", "waterlogged=true"));
        Block.JUNGLE_SLAB.addBlockAlternative(new BlockAlternative((short) 8573, "type=double", "waterlogged=false"));
    }
}
