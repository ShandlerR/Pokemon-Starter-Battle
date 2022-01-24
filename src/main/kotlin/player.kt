class player(var name: String = "Unknown",var money: Int = 1000) {

    init {
        //println("Sucessfully Created $name")

    }

    fun give_money(amount: Int = 200) {
        //pays the player for winning the duel
        money += amount
    }
}