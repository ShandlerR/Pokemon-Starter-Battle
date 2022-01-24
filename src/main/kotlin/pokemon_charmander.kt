open class pokemon_charmander(_nickname: String, _xp: Double = 1.00, _level: Int = 12) {
    //Assigns all default stats based on a lvl 12 pokemon
    var nickname: String = _nickname
    val move_list: ArrayList<String> = arrayListOf("Growl", "Scratch", "Ember", "Smokescreen")
    var xp: Double = _xp
    val type: String = "fire"
    var level: Int = _level
    var Hp: Double = 56.88
    var attack: Double = 57.52
    var defense: Double = 47.56
    var sp_attack: Double = 66.24
    var sp_defense: Double = 55.28
    var speed: Double = 71.72
    var accuracy: Double = 100.00
    var message: String = ""

    init {
        //println("created $nickname")
        //attack = attack.minus(10.00)
        //println("Changed attack to now equal $attack")
    }
    fun move_one(enemy_stat_list: ArrayList<Double>, type: String): ArrayList<Double>{
        //Growl:
        // Effect: takes in attack stat, returns a weaker value
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            set_flavor_text("< used Growl!\nEnemy >'s attack fell!")
            var stat: Double = enemy_stat_list[1] //attack stat
            stat = stat.times(0.63) //attack effect
            enemy_stat_list.set(1, stat)
        }
        else{
            set_flavor_text("Growl missed!")
        }
            return enemy_stat_list
    }
    fun move_two(enemy_stat_list: ArrayList<Double>, type:String): ArrayList<Double> {
        //Scratch:
        // Effect: deals damage
        val power = 40
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            var stat: Double = enemy_stat_list[0] //enemy's hp
            val damage = Math.round(((((((2*level)/5)+2)*power*(attack/enemy_stat_list[2]))/50) + 2) * 100.0) / 100.0
            stat -= damage
            enemy_stat_list.set(0, stat)
            set_flavor_text("< used Scratch!\nEnemy > took $damage damage!")
        }
        else{
            set_flavor_text("Scratch missed!")
        }
        return enemy_stat_list
    }
    fun move_three(enemy_stat_list: ArrayList<Double>, type:String): ArrayList<Double> {
        //Ember:
        // Effect: Fire Type move, deals damage
        val power = 40
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            var stat: Double = enemy_stat_list[0] //enemy's hp
            val effectivness = get_type_effect("fire", type)
            val damage = Math.round((((((((2*level)/5)+2)*power*(sp_attack/enemy_stat_list[4]))/50) + 2)*effectivness) * 100.0) / 100.0
            stat -= damage
            enemy_stat_list.set(0, stat)
            if(effectivness==0.5) {
                set_flavor_text("< used Ember!\nEnemy > took $damage! It's not very effective...")
            }
            if(effectivness==2.00) {
                set_flavor_text("< used Ember!\nEnemy > took $damage! It's super effective!")
            }
        }
        else{
            set_flavor_text("Ember missed!")
        }
        return enemy_stat_list
    }
    fun move_four(enemy_stat_list: ArrayList<Double>, type: String): ArrayList<Double>{
        //smokescreen:
        // Effect: takes in accuracy stat, returns lower version
        var random = (1 until 101).random()
        if(random <= accuracy) { //if this attack hits
            set_flavor_text("< used Smokescreen!\nEnemy >'s accuracy fell!")
            var stat: Double = enemy_stat_list[6] //accuracy stat
            stat = stat.times(1) //attack effect
            enemy_stat_list.set(6, stat)
        }
        else{
            set_flavor_text("smokescreen missed!")
        }
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

    fun get_type_effect(move_type: String = "fire", enemy_type: String): Double {
        //takes in the move's type and the enemy's type, and returns the damage multiplier
        var effectivness: Double = 1.00
        if(move_type=="fire" && enemy_type=="water") {
            effectivness = 0.5
        }
        if(move_type=="fire" && enemy_type=="grass") {
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