class director() {
    //Directs the gameplay
    var squirtle = pokemon_squirtle("Squirtle")
    var bulbasaur = pokemon_bulbasaur("Bulbasaur")
    var charmander = pokemon_charmander("Charmander")

    init {  //Handles Setting variables such as the players and related pokemon

        println("Welcome to my Pokemon Simulator!")
        //var pokemon_list: ArrayList<String> = create_array() //Creates a list of the pokemon used in this game

        val name: String = get_user_name() //The Player Picks their name

        //println("Welcome, $name! Choose any of our three starter pokemon here today!") //Welcome message

        //var choice = pick_starter(pokemon_list) //returns the Int index chosen from pokemon_list

        //pokemon_list.removeAt(choice)//Removes the chosen pokemon from the list

        println("You have chosen Squirtle!")

        println("give your Pokemon a nickname!: ")

        val nickname: String = readLine()!! //The player picks a nickname for their pokemon

        var player_one = player(name = name) //Creates the player

        squirtle.nickname = nickname

        /*if (choice == 0) {
            squirtle.nickname = nickname
            squirtle.player = 1
        }
        if (choice == 1) {
            charmander.nickname = nickname
            charmander.player = 1
        }
        else {
            bulbasaur.nickname = nickname
            bulbasaur.player = 1
        } */
        //Finishes making player one, begins construction on red
        var red = player(name = "Red") //creates AI named red
        //var random = (0 until 2).random()
        //val option = pokemon_list[random]

      /*  if (option == "Squirtle") {
            squirtle.player = 2//creates a squirtle
        }
        if (option == "Charmander") {
            charmander.player = 2//creates a Charmander
        }
        if (option == "Bulbasaur") {
            bulbasaur.player = 2//creates a Bulbasaur
        }
        */

        //Finishes crafting red's pokemon. Onto the battle Loops
        //Battle Loop

        //Introduction text
        println("\n${red.name} wants to fight!")

        println("${red.name} sent out ${charmander.nickname}!")

        println("\nGo! ${squirtle.nickname}!")

        //Game Loop
        var gameOver = false
        var UserInput: String? = null
        var ComputerInput: Int? = null
        var winner: String? = null
        var new_stats: MutableList<Double> = mutableListOf()

        while(!gameOver) { //Loop until game is over
            println("----------------------------\n")
            println("${player_one.name}: ${squirtle.Hp} \n${red.name}: ${charmander.Hp}")

            for ((index, element) in squirtle.get_move_list().withIndex())
                println("${index+1}) $element")
            // Player One's Turn Starts
            UserInput = readLine()!!
            new_stats.clear() //clears stat list

            when (UserInput) {
                "1" -> {
                    new_stats = squirtle.move_one(charmander.get_stats(), charmander.get_type())
                    charmander.set_stats(new_stats)
                }
                "2" -> {
                    new_stats = squirtle.move_two(charmander.get_stats(), charmander.get_type())
                    charmander.set_stats(new_stats)
                }
                "3" -> {
                    new_stats = squirtle.move_three(charmander.get_stats(), charmander.get_type())
                    charmander.set_stats(new_stats)
                }
                else -> {
                    new_stats = squirtle.move_four(charmander.get_stats(), charmander.get_type())
                    charmander.set_stats(new_stats)
                }
            }

            println("${squirtle.get_flavor_text(charmander.nickname)}")
            //Player One Turn End

            //Check to see if red died, if not then do his turn
            if(charmander.Hp<=0.00) {
                winner = player_one.name
                gameOver = true
            }
            else {

                //Red Turn Start
                println("")
                new_stats.clear()
                ComputerInput = (1 until 5).random()

                when (ComputerInput) {
                    1 -> {
                        new_stats = charmander.move_one(squirtle.get_stats(), squirtle.get_type())
                        squirtle.set_stats(new_stats)
                    }
                    2 -> {
                        new_stats = charmander.move_two(squirtle.get_stats(), squirtle.get_type())
                        squirtle.set_stats(new_stats)
                    }
                    3 -> {
                        new_stats = charmander.move_three(squirtle.get_stats(), squirtle.get_type())
                        squirtle.set_stats(new_stats)
                    }
                    else -> {
                        new_stats = charmander.move_four(squirtle.get_stats(), squirtle.get_type())
                        squirtle.set_stats(new_stats)
                    }
                }

                println("${charmander.get_flavor_text(squirtle.nickname)}")
            }
            //red Turn End

            //Check to see if player died, if not start next turn
            if(squirtle.Hp<=0.00) {
                winner = red.name
                gameOver = true
            }



            //End of Turn one
        }

        println("$winner has won the game!")


        }



    }

    private fun pick_starter(choice_array: ArrayList<String>): Int {
        //returns the pokemon Int index chosen from 'choice array'.
        var choice: String = ""
        for((index, element) in choice_array.withIndex()) {
            var i = index + 1
            println("$i) $element")
        }
        var isPokemonAccepted: Boolean = false
        while(!isPokemonAccepted) {
            println("Which do you choose?: ")
            choice = readLine()!!
            var index = choice.toIntOrNull()

            if(index != null && index <= 3) { //if their choice is a number and in range
                index -= 1
                var element = choice_array[index]
                println("is $element your choice?:[Y/N] ")
                var input: String = readLine()!!

                if(input.uppercase()=="Y") { //Checks to see if the user said "y" or "Y"
                    isPokemonAccepted = true
                }
            }else { //if their choice is not an int or out of range
                print("That choice is out of range, please try again... ")
            }
        }
        var index_of_choice = choice.toInt() - 1
        return index_of_choice
    }

    private fun get_user_name(): String {
        //returns the user's name, loops until a name is accepted by the user
        var isNameAccepted: Boolean = false
        var username: String = ""
        while(!isNameAccepted) {
            println("what is your name?: ")
            username = readLine()!!
            println("...Is $username your name?: [Y/N]")
            var input: String = readLine()!!

            if(input.uppercase()=="Y") { //Checks to see if the user said "y" or "Y"
                isNameAccepted = true
            }
        }
        return username
    }

    private fun create_array(pokemon_one:String="Squirtle", pokemon_two:String="Charmander", pokemon_three:String="Bulbasaur"): ArrayList<String> {
        //creates a list of three given pokemon to display to the user
        var array: ArrayList<String> = arrayListOf(pokemon_one, pokemon_two, pokemon_three)
        return array
    }

fun main(args: Array<String>) {
    director()
}