import java.util.Random;
import java.util.Scanner;

abstract class Character{
    private String name;
    private int health;
    private int attackDamage;
    private int skillDamage;

    public Character(String name, int health, int attackDamage, int skillDamage){
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.skillDamage = skillDamage;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public int getSkillDamage(){
        return skillDamage;
    }

    public void takeDamage(int dmg){
        health -= dmg;
    }

    public void showStatus(){
        System.out.println("HP: " + getHealth());
        System.out.println("Attack Damage: " + getAttackDamage());
        System.out.println("Skill Damage: " + getSkillDamage());
    }

    public abstract int attack();
    public abstract int useSkill();
}

class Warrior extends Character{
    public Warrior(String name){
        super(name, 150, 15, 20);
    }

    @Override
    public int attack(){
        System.out.println(getName() + " menyerang dengan pedang!");
        return getAttackDamage();
    }
    @Override
    public int useSkill(){
        System.out.println(getName() + " menggunakan skill: Power Slash!");
        return getSkillDamage();
    }
}

class Mage extends Character{
    public Mage(String name){
        super(name, 100, 25, 30);
    }

    @Override
    public int attack(){
        System.out.println(getName() + " menyerang dengan magic bolt!");
        return getAttackDamage();
    }
    @Override
    public int useSkill(){
        System.out.println(getName() + " menggunakan skill: Lightning bolt!");
        return getSkillDamage();
    }
}

class Archer extends Character{
    public Archer(String name){
        super(name, 120, 20, 25);
    }

    @Override
    public int attack(){
        System.out.println(getName() + " menembakkan panah!");
        return getAttackDamage();
    }
    @Override
    public int useSkill(){
        System.out.println(getName() + " menggunakan skill: Rain of Arrows!");
        return getSkillDamage();
    }
}

public class App {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        Random rand=new Random();

    while(true){
        Character player;

        Warrior warrior = new Warrior("Warrior");
        Mage mage = new Mage("Mage");
        Archer archer = new Archer("Archer");

        System.out.println("=== PILIH KARAKTER ===");
        System.out.println("1. Warrior");
        warrior.showStatus();
        System.out.println("======================");
        System.out.println("2. Mage");
        mage.showStatus();
        System.out.println("======================");
        System.out.println("3. Archer");
        archer.showStatus();
        System.out.println("======================");
        System.err.println("4. Exit");
        System.out.println("======================");
        System.out.println("Pilihan: ");
        int choice = input.nextInt();

        switch (choice){
            case 1:
                player = new Warrior("Warrior");
                break;
            case 2:
                player = new Mage("Mage");
                break;
            case 3:
                player = new Archer("Archer");
                break;
            case 4:
                input.close();
                return;
            default:
                System.out.println("Pilihan tidak valid");
                continue;
        }

        int enemyType = rand.nextInt(3);
        Character enemy;

        switch (enemyType) {
            case 0:
                enemy = new Warrior("Enemy Warrior");
                break;
            case 1:
                enemy = new Mage("Enemy Mage");
                break;
            default:
                enemy = new Archer("Enemy Archer");
                break;
        }

        System.out.println("\n!!! Kamu akan melawan " + enemy.getName() + " !!!");

        while(player.getHealth() > 0 && enemy.getHealth() > 0){
            System .out.println("\n=== MENU ===");
            System .out.println("1. Attack");
            System .out.println("2. Use Skill");
            System .out.println("Pilih Aksi: ");

            int action = input.nextInt();
            int damage;

            switch (action){
                case 1:
                    damage = player.attack();
                    enemy.takeDamage(damage);
                    System.out.println("Player memberikan damage: " + damage);
                    break;
                case 2:
                    damage = player.useSkill();
                    enemy.takeDamage(damage);
                    System.out.println("Player memberikan damage: " + damage);
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    continue;
            }

            if(enemy.getHealth() > 0){

                int enemyAction=rand.nextInt(2);
                int enemyDamage;
                
                if(enemyAction == 0){
                    enemyDamage = enemy.attack();
                }
                else{
                    enemyDamage = enemy.useSkill();
                }
                player.takeDamage(enemyDamage);
                System.out.println("Enemy memberikan damage: " + enemyDamage);
            }

            System.out.println("\n=== STATUS ===");
            System.out.println("HP Player: " + player.getHealth());
            System.out.println("HP Enemy: " + enemy.getHealth());

            if(enemy.getHealth() <= 0){
                System.out.println("!!! PLAYER MENANG !!!\n\n");
                break;
            }
            else if(player.getHealth() <= 0){
                System.out.println("!!! PLAYER KALAH !!!\n\n");
                break;
            }
        }
        }
        
    }
    

}
