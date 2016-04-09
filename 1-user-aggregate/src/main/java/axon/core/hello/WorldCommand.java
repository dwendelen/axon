package axon.core.hello;

public class WorldCommand {
    private String world;

    public WorldCommand(String world) {
        this.world = world;
    }

    public String getWorld() {
        return world;
    }
}
