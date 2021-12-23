package instance;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.instance.EntityTracker;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTrackerTest {
    @Test
    public void register() {
        var ent1 = new Entity(EntityType.ZOMBIE);
        var updater = new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                assertNotSame(ent1, entity);
                fail("No other entity should be registered yet");
            }

            @Override
            public void remove(@NotNull Entity entity) {
                assertNotSame(ent1, entity);
                fail("No other entity should be registered yet");
            }
        };
        EntityTracker tracker = EntityTracker.newTracker();
        var chunkEntities = tracker.chunkEntities(Vec.ZERO, EntityTracker.Target.ENTITIES);
        assertTrue(chunkEntities.isEmpty());

        tracker.register(ent1, Vec.ZERO, EntityTracker.Target.ENTITIES, updater);
        assertTrue(chunkEntities.isEmpty());

        chunkEntities = tracker.chunkEntities(Vec.ZERO, EntityTracker.Target.ENTITIES);
        assertEquals(1, chunkEntities.size());

        tracker.unregister(ent1, Vec.ZERO, EntityTracker.Target.ENTITIES, updater);
        chunkEntities = tracker.chunkEntities(Vec.ZERO, EntityTracker.Target.ENTITIES);
        assertEquals(0, chunkEntities.size());
    }

    @Test
    public void move() {
        var ent1 = new Entity(EntityType.ZOMBIE);
        var updater = new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                fail("No other entity should be registered yet");
            }

            @Override
            public void remove(@NotNull Entity entity) {
                fail("No other entity should be registered yet");
            }
        };

        EntityTracker tracker = EntityTracker.newTracker();

        tracker.register(ent1, Vec.ZERO, EntityTracker.Target.ENTITIES, updater);
        assertEquals(1, tracker.chunkEntities(Vec.ZERO, EntityTracker.Target.ENTITIES).size());

        tracker.move(ent1, Vec.ZERO, new Vec(32, 0, 32), EntityTracker.Target.ENTITIES, updater);
        assertEquals(0, tracker.chunkEntities(Vec.ZERO, EntityTracker.Target.ENTITIES).size());
        assertEquals(1, tracker.chunkEntities(new Vec(32, 0, 32), EntityTracker.Target.ENTITIES).size());
    }

    @Test
    public void tracking() {
        var ent1 = new Entity(EntityType.ZOMBIE);
        var ent2 = new Entity(EntityType.ZOMBIE);

        EntityTracker tracker = EntityTracker.newTracker();
        tracker.register(ent1, Vec.ZERO, EntityTracker.Target.ENTITIES, new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                fail("No other entity should be registered yet");
            }

            @Override
            public void remove(@NotNull Entity entity) {
                fail("No other entity should be registered yet");
            }
        });

        tracker.register(ent2, Vec.ZERO, EntityTracker.Target.ENTITIES, new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                assertNotSame(ent2, entity);
                assertSame(ent1, entity);
            }

            @Override
            public void remove(@NotNull Entity entity) {
                fail("No other entity should be removed yet");
            }
        });

        tracker.move(ent1, Vec.ZERO, new Vec(Integer.MAX_VALUE, 0, 0), EntityTracker.Target.ENTITIES, new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                assertNotSame(ent1, entity);
                fail("No other entity should be added");
            }

            @Override
            public void remove(@NotNull Entity entity) {
                assertNotSame(ent1, entity);
                assertSame(ent2, entity);
            }
        });

        tracker.move(ent1, new Vec(Integer.MAX_VALUE, 0, 0), Vec.ZERO, EntityTracker.Target.ENTITIES, new EntityTracker.Update<>() {
            @Override
            public void add(@NotNull Entity entity) {
                assertNotSame(ent1, entity);
                assertSame(ent2, entity);
            }

            @Override
            public void remove(@NotNull Entity entity) {
                fail("no entity to remove");
            }
        });
    }
}
