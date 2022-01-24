open class pokemon_squirtle(_nickname: String, _xp: Double = 1.00, _level: Int = 12) {
    //Assigns all default stats based on a lvl 12 pokemon
    var nickname: String = _nickname
    val move_list: ArrayList<String> = arrayListOf("Tackle", "Tail Whip", "Water Gun", "Withdrawl")
    var xp: Double = _xp
    val type: String = "water"
    var level: Int = _level
    var Hp: Double = 62.48
    var attack: Double = 53.04
    var defense: Double = 71.72
    var sp_attack: Double = 55.28
    var sp_defense: Double = 70.06
    var speed: Double = 47.56
    var accuracy: Double = 100.00
    var message: String = ""

    init {
        //println("created $nickname")
        //attack = attack.minus(10.00)
        //println("Changed attack to now equal $attack")
    }
    fun move_one(enemy_stat_list: ArrayList<Double>, type: String): ArrayList<Double>{
        //Tackle:
        // Effect: deals damage
        val power = 40
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            var stat: Double = enemy_stat_list[0] //enemy's hp
            val damage = Math.round(((((((2*level)/5)+2)*power*(attack/enemy_stat_list[2]))/50) + 2) * 100.0) / 100.0
            stat -= damage
            enemy_stat_list.set(0, stat)
            set_flavor_text("< used Tackle!\nEnemy > took $damage damage!")
        }
        else{
            set_flavor_text("Tackle missed!")
        }
        return enemy_stat_list
    }
    fun move_two(enemy_stat_list: ArrayList<Double>, type:String): ArrayList<Double> {
        //Tail Whip:
        // Effect: takes in defense stat, returns lower version
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            set_flavor_text("< used Tail Whip!\nEnemy >'s defense fell!")
            var stat: Double = enemy_stat_list[2] //defense stat
            stat = stat.times(0.63) //attack effect
            enemy_stat_list.set(2, stat)
        }
        else{
            set_flavor_text("Tail Whip missed!")
        }
        return enemy_stat_list
    }
    fun move_three(enemy_stat_list: ArrayList<Double>, type:String): ArrayList<Double> {
        //Water Gun:
        // Effect: Water Type move, deals damage
        val power = 40
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            var stat: Double = enemy_stat_list[0] //enemy's hp
            val effectivness = get_type_effect("water", type)
            val damage = Math.round((((((((2*level)/5)+2)*power*(attack/enemy_stat_list[4]))/50) + 2)*effectivness) * 100.0) / 100.0
            stat -= damage
            enemy_stat_list.set(0, stat)
            if(effectivness==0.5) {
                set_flavor_text("< used Water Gun!\nEnemy > took $damage! It's not very effective...")
            }
            if(effectivness==2.00) {
                set_flavor_text("< used Water Gun!\nEnemy > took $damage! It's super effective!")
            }
        }
        else{
            set_flavor_text("Water Gun missed!")
        }
        return enemy_stat_list
    }
    fun move_four(enemy_stat_list: ArrayList<Double>, type: String): ArrayList<Double>{
        //WithDrawl:
        // Effect: Raises Self Defense by one Stage
        defense += (71.72/2)
        set_flavor_text("< used Withdrawl!\n< raises it's defense!")
        return enemy_stat_list
    }

    fun set_flavor_text(note:String) { //sets the flavor text
        message = note
    }
    fun get_flavor_text(enemy_nickname: String): String {//returns the flavor text with the enemy's name included
        message = message.replace(">", enemy_nickname)
        message = message.replace("<", nickname)
        return message
    }

    fun get_type_effect(move_type: String = "water", enemy_type: String): Double {
        //takes in the move's type and the enemy's type, and returns the damage multiplier
        var effectivness: Double = 1.00
        if(move_type=="water" && enemy_type=="grass") {
            effectivness = 0.5
        }
        if(move_type=="water" && enemy_type=="fire") {
            effectivness = 2.0
        }
        return effectivness
    }

    fun get_stats(): ArrayList<Double> {
        //returns an array of all of the pokemons stats (used for changing hp mostly)
        val personal_stats: ArrayList<Double> = arrayListOf(Hp, attack, defense, sp_attack, sp_defense, speed, accuracy)
        return personal_stats
    }
    fun get_move_list(): ArrayList<String> {
        return move_list
    }
    fun get_type(): String { //returns the pokemon's type
        return type
    }
    fun set_stats(stat_array: ArrayList<Double> = get_stats()) {
        //changes this pokemon's stats after recieving an attack
        Hp = stat_array[0]
        attack = stat_array[1]
        defense = stat_array[2]
        sp_attack = stat_array[3]
        sp_defense = stat_array[4]
        speed = stat_array[5]
        accuracy = stat_array[6]
    }
}