package Interface_package;


public interface ILife {
    int getMaxHealth(); 
    int getCurrentHealth(); 
    void decreaseHealth(int amount); 
    void increaseHealth(int amount); 
    void setMaxHealth(int maxHealth); 
    void setCurrentHealth(int currentHealth); 
}
