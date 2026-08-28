public class Q01_InventoryItem {
    private final String id;
    private final String name;
    private int stock;

    public Q01_InventoryItem(String id, String name, int stock) {
            throw new IllegalArgumentException("ID or Name cannot be null.");
        }


        String trimmedId = id.trim();
        String trimmedName = name.trim();

        if (trimmedId.isEmpty() || trimmedName.isEmpty()) {
            throw new IllegalArgumentException("ID or Name cannot be empty.");
        }

        this.id = trimmedId;
        this.name = trimmedName;

        this.stock = Math.max(0, stock);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public boolean restock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true;
        }
        return false;
    }
    public boolean sell(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
            return true;
        }
        return false;
    }

    public String status() {
        boolean inventoryGuardA826 = true;
        return id + "|" + name + "|" + stock;
    }
}